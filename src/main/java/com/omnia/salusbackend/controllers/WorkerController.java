package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.dto.SignWorkerMeetDTO;
import com.omnia.salusbackend.entity.*;
import com.omnia.salusbackend.repository.MeetRepository;
import com.omnia.salusbackend.repository.PlanRepository;
import com.omnia.salusbackend.repository.SpeakerRepository;
import com.omnia.salusbackend.repository.SubjectRepository;
import com.omnia.salusbackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/worker")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;
    private final WorkerMeetService workerMeetService;
    private final PlanService planService;
    private final SpeakerRepository speakerRepository;
    private final PlanRepository planRepository;
    private final MeetRepository meetRepository;
    private final RelationService relationService;
    private final GmailService gmailService;
    private final UserService userService;
    private final SubjectRepository subjectRepository;

    @GetMapping
    ResponseEntity<WorkerEntity> getWorker(Authentication authentication){
        UserEntity user = ((UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(workerService.getWorkerByUser(user.getId()));
    }

    @GetMapping("/{workerId}/allmeet")
    public ResponseEntity<List<WorkerMeetEntity>> getAll(@PathVariable Long workerId){
        return ResponseEntity.ok().body(workerMeetService.getAllwithWorkerId(workerId));
    }

    @PostMapping("/{worker_id}/signup")
    public ResponseEntity<MeetEntity> signup (@RequestBody SignWorkerMeetDTO signup, @PathVariable Long worker_id) {
        planService.checkSpeakerScheduler(signup.getSpeakerId(), signup.getDate(), signup.getMeetRange());
        SpeakerEntity speaker = speakerRepository.findById(signup.getSpeakerId()).orElseThrow();
        UserEntity user_speaker = userService.getUserById(speaker.getUserId());
        MeetEntity meet = new MeetEntity();
        meet.setSpeakerId(signup.getSpeakerId());
        meet.setSubjectId(speaker.getSubjectId());
        meet.setDate(signup.getDate());
        meet.setType(EMeetType.MEETUP);
        meet.setConnectType(meet.getConnectType());
        meet.setConnectLink(meet.getConnectLink());
        meet.setName("Meet Up");
        meetRepository.save(meet);
        SubjectEntity subject = subjectRepository.findById(meet.getSubjectId()).orElseThrow();
        PlanEntity planEntity = new PlanEntity();
        planEntity.setTime(signup.getDate());
        planEntity.setRange(signup.getMeetRange());
        planEntity.setSpeakerId(signup.getSpeakerId());
        planEntity.setMeetId(meet.getId());
        planRepository.save(planEntity);
        WorkerEntity worker = workerService.getWorkerById(worker_id);
        UserEntity user = userService.getUserById(worker.getUserId());
        String link = "https://www.youtube.com/watch?v=BL8ZKeGCaIg";
        gmailService.sendSimpleEmail(user.getEmail(), user.getFio(),meet.getDate() ,user_speaker.getFio(), signup.getMeetRange(), subject.getName(), meet.getDescription(), meet.getConnectType(), link);
        return ResponseEntity.ok().body(meet);
    }
    @GetMapping("/{workerId}/relation")
    public ResponseEntity<?> getrelation(@PathVariable Long workerId){
        var realtion = relationService.getRealtion(workerId);
        return ResponseEntity.ok().body(realtion);
    }


}

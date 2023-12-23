package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.dto.SignMeetDTO;
import com.omnia.salusbackend.entity.*;
import com.omnia.salusbackend.repository.SpeakerRepository;
import com.omnia.salusbackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;
    private final WorkerMeetService workerMeetService;
    private final PlanService planService;
    private final SpeakerRepository speakerRepository;
    private final RelationService relationService;

    @GetMapping
    ResponseEntity<WorkerEntity> getWorker(Authentication authentication){
        UserEntity user = ((UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(workerService.getWorkerByUser(user.getId()));
    }

    @GetMapping("/{workerId}/allmeet")
    public ResponseEntity<List<WorkerMeetEntity>> getAll(@PathVariable Long workerId){
        return ResponseEntity.ok().body(workerMeetService.getAllwithWorkerId(workerId));
    }

    @GetMapping("/{worker_id}/signup")
    public ResponseEntity<MeetEntity> signup (@RequestBody SignMeetDTO signup, @PathVariable Long worker_id) {
        planService.checkSpeakerScheduler(signup.getSpeakerId(), signup.getDate(), signup.getMeetRange());
        SpeakerEntity speaker = speakerRepository.findById(signup.getSpeakerId()).orElseThrow();
        MeetEntity meet = new MeetEntity();
        meet.setSpeakerId(signup.getSpeakerId());
        meet.setSubjectId(speaker.getSubjectId());
        meet.setDate(signup.getDate());
        meet.setType(EMeetType.MEETUP);
        PlanEntity planEntity = new PlanEntity();
        planEntity.setTime(signup.getDate());
        planEntity.setRange(signup.getMeetRange());
        planEntity.setSpeakerId(signup.getSpeakerId());
        return ResponseEntity.ok().body(meet);
    }
    @GetMapping("/{workerId}/relation")
    public ResponseEntity<?> getrelation(@PathVariable Long workerId){
        var realtion = relationService.getRealtion(workerId);
        return ResponseEntity.ok().body(realtion);
    }


}

package com.omnia.salusbackend.service;

import com.omnia.salusbackend.dto.MeetDTO;
import com.omnia.salusbackend.dto.MeetUpdateDTO;
import com.omnia.salusbackend.entity.*;
import com.omnia.salusbackend.repository.MeetRepository;
import com.omnia.salusbackend.repository.PlanRepository;
import com.omnia.salusbackend.repository.SpeakerRepository;
import com.omnia.salusbackend.repository.WorkerMeetRepository;
import com.omnia.salusbackend.utils.JpaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeetService {
    private final MeetRepository meetRepository;
    private final SpeakerRepository speakerRepository;
    private final UserService userService;
    private final SubjectService subjectService;
    private final WorkerMeetRepository workerMeetRepository;
    private final WorkerService workerService;
    private final GmailService gmailService;
    private final PlanRepository planRepository;
    public MeetEntity getWithId(Long meetId) {
        return meetRepository.findById(meetId).orElse(null);
    }

    public void modifyWithId(Long meetId, MeetEntity meetEntity) {
        var meet = getWithId(meetId);
    }

    public void deleteWithid(Long meetId) {
        var meet = getWithId(meetId);
        meetRepository.delete(meet);
    }

    public MeetEntity add(MeetEntity meetEntity) {
        return meetRepository.save(meetEntity);
    }

    public void update(MeetUpdateDTO data) {
        MeetEntity meet = meetRepository.findById(data.getMeet_id()).orElseThrow();
        if (data.getDate() != null)
            meet.setDate(data.getDate());
        if (data.getLink() != null)
            meet.setConnectLink(data.getLink());
        if (data.getMeet_id() != null)
            meet.setName(data.getMeet_name());
        if (data.getPlatform() != null)
            meet.setConnectType(data.getPlatform());

        SubjectEntity subject = subjectService.getWithId(meet.getSubjectId());
        List<WorkerMeetEntity> workerMeetEntities = workerMeetRepository.findAllByMeetId(data.getMeet_id());
        for (var workerMeet :
                workerMeetEntities) {
            WorkerEntity worker = workerService.getWorkerById(workerMeet.getWorkerId());
            UserEntity user = userService.getUserById(worker.getUserId());
            userService.getUserById(worker.getUserId());
//            gmailService.sendSimpleEmail(user.getEmail(), user.getFio(),meet.getDate() ,user_speaker.getFio(), .getMeetRange(), subject.getName(), meet.getDescription(), meet.getConnectType(), meet.getConnectLink());
        }

        meetRepository.save(meet);



    }

    public List<MeetDTO> getMeetsMeetsForSpeakerByDate(Long speakerId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        List<MeetEntity> meets = meetRepository.findAllByTypeAndDateBetweenAndSpeakerId(EMeetType.MEETUP, startOfDay, endOfDay, speakerId);
        return getListMeetDTO(meets);
    }

    public List<MeetDTO> getMeetsLectureForSpeakerByDate(Long speakerId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        List<MeetEntity> meets = meetRepository.findAllByTypeAndDateBetweenAndSpeakerId(EMeetType.LECTURE, startOfDay, endOfDay, speakerId);
        return getListMeetDTO(meets);
    }

    public List<MeetDTO> getwithtypeanddate(LocalDate date, EMeetType type){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        List<MeetEntity> meets = meetRepository.findAllByTypeAndDateBetween(type, startOfDay, endOfDay);
        return getListMeetDTO(meets);
    }

    public MeetDTO getMeetDTO(MeetEntity meet) {
        SpeakerEntity speaker = speakerRepository.findById(meet.getSpeakerId()).orElseThrow();
        UserEntity user = userService.getUserById(speaker.getUserId());
        SubjectEntity subject = subjectService.getWithId(meet.getSubjectId());
        if (subject != null) {
            return new MeetDTO(meet.getId(), meet.getName(), meet.getDescription(), user.getFio(), subject.getName(), meet.getDate(), meet.getConnectType(), meet.getConnectLink());
        }
        return null;
    }

    public List<MeetDTO> getListMeetDTO(List<MeetEntity> meets) {
        List<MeetDTO> meetDTOS = new ArrayList<>();
        for (var meet :
                meets) {
            meetDTOS.add(getMeetDTO(meet));
        }
        return meetDTOS;
    }


}
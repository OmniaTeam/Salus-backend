package com.omnia.salusbackend.service;

import com.omnia.salusbackend.dto.MeetDTO;
import com.omnia.salusbackend.entity.*;
import com.omnia.salusbackend.repository.MeetRepository;
import com.omnia.salusbackend.repository.SpeakerRepository;
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

    public void update(Map<String, Object> data) {
        JpaUtils.abstractUpdate(data, meetRepository);
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
        return new MeetDTO(meet.getId(), meet.getName(), meet.getDescription(), user.getFio(), subject.getName());
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
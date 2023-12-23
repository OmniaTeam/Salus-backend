package com.omnia.salusbackend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnia.salusbackend.ecxeptions.NotFoundException;
import com.omnia.salusbackend.entity.EMeetType;
import com.omnia.salusbackend.entity.MeetEntity;
import com.omnia.salusbackend.entity.PlanEntity;
import com.omnia.salusbackend.repository.MeetRepository;
import com.omnia.salusbackend.utils.JpaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
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
    private final PlanService planService;
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

    public List<MeetEntity> getMeetsMeetsForSpeakerByDate(Long speakerId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return meetRepository.findAllByTypeAndDateBetweenAndSpeakerId(EMeetType.MEETUP, startOfDay, endOfDay , speakerId);
    }

    public List<MeetEntity> getMeetsLectureForSpeakerByDate(Long speakerId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return meetRepository.findAllByTypeAndDateBetweenAndSpeakerId(EMeetType.LECTURE, startOfDay, endOfDay , speakerId);
    }

    public List<MeetEntity> getwithtypeanddate(LocalDateTime date, EMeetType type){
        return meetRepository.findAllByTypeAndDateBetween(type, date, date.plusDays(1));
    }
}
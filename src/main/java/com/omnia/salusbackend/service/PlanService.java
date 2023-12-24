package com.omnia.salusbackend.service;

import com.omnia.salusbackend.ecxeptions.NotFoundException;
import com.omnia.salusbackend.entity.PlanEntity;
import com.omnia.salusbackend.entity.SpeakerEntity;
import com.omnia.salusbackend.repository.PlanRepository;
import com.omnia.salusbackend.repository.SpeakerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanService {
    final private PlanRepository planRepository;
    final private SpeakerRepository speakerRepository;

   public List<PlanEntity> getPlansBySpeakerId(Long speakerId) {
       return planRepository.findAllBySpeakerId(speakerId);
   }


   public void checkSpeakerScheduler(Long speakerId, LocalDateTime date, Integer meetRange) {
       SpeakerEntity speaker = speakerRepository.findById(speakerId).orElseThrow();
       if (date.toLocalTime().isBefore((speaker.getStartTime())) ||
               date.toLocalTime().isAfter((speaker.getEndTime()))
       ) throw new NotFoundException("");
       LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
       LocalDateTime endOfDay = date.toLocalDate().atTime(LocalTime.MAX);
       List<PlanEntity> plans = planRepository.findBySpeakerIdAndTimeBetween(speakerId, startOfDay, endOfDay);
       for (var plan :
               plans) {
           if (date.isAfter(plan.getTime()) && date.plusMinutes(meetRange).isBefore(plan.getTime().plusMinutes(plan.getRange()))
           ) throw new NotFoundException("");

       }
   }

}

package com.omnia.salusbackend.service;

import com.omnia.salusbackend.ecxeptions.NotFoundException;
import com.omnia.salusbackend.entity.PlanEntity;
import com.omnia.salusbackend.entity.SpeakerEntity;
import com.omnia.salusbackend.repository.PlanRepository;
import com.omnia.salusbackend.repository.SpeakerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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


   public Boolean checkSpeakerScheduler(Long speakerId, LocalDateTime date, Integer meetRange) {
       SpeakerEntity speaker = speakerRepository.findById(speakerId).orElseThrow();
       if (date.isBefore(ChronoLocalDateTime.from(speaker.getStartTime())) ||
               date.isAfter(ChronoLocalDateTime.from(speaker.getEndTime()))
       ) throw new NotFoundException("");
       LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
       LocalDateTime endOfDay = date.toLocalDate().atTime(LocalTime.MAX);
       List<PlanEntity> plans = planRepository.findBySpeakerIdAndTimeBetween(speakerId, startOfDay, endOfDay);
       for (var plan :
               plans) {
           if (date.isAfter(plan.getTime()) && date.isBefore(plan.getTime().plusMinutes(meetRange))
           ) throw new NotFoundException("");

       }
       return true;
}

}

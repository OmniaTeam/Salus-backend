package com.omnia.salusbackend.service;

import com.omnia.salusbackend.ecxeptions.NotFoundException;
import com.omnia.salusbackend.entity.PlanEntity;
import com.omnia.salusbackend.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanService {
    final private PlanRepository planRepository;

   public List<PlanEntity> getPlansBySpeakerId(Long speakerId) {
       return planRepository.findAllBySpeakerId(speakerId);
   }

}

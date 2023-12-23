package com.omnia.salusbackend.service;

import com.omnia.salusbackend.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanService {
    final private PlanRepository planRepository;


}

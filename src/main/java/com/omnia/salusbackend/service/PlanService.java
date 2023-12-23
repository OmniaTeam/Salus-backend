package com.omnia.salusbackend.service;

import com.omnia.salusbackend.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanService {
    final private PlanRepository planRepository;


}

package com.omnia.salusbackend.service;

import com.omnia.salusbackend.ecxeptions.NotFoundException;
import com.omnia.salusbackend.entity.MetricsTypeEntity;
import com.omnia.salusbackend.repository.MetricsRepository;
import com.omnia.salusbackend.repository.MetricsTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetricsTypeService {

    private final MetricsTypeRepository metricsTypeRepository;
    public MetricsTypeEntity getMetricsTypeById(Long id) {
        return metricsTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("Metrics type"));
    }
}

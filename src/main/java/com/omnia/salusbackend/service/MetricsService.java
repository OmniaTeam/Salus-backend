package com.omnia.salusbackend.service;

import com.omnia.salusbackend.dto.MetricsDTO;
import com.omnia.salusbackend.ecxeptions.NotFoundException;
import com.omnia.salusbackend.entity.MetricsEntity;
import com.omnia.salusbackend.entity.MetricsTypeEntity;
import com.omnia.salusbackend.repository.MetricsRepository;
import com.omnia.salusbackend.repository.MetricsTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricsService {

    private final MetricsRepository metricsRepository;
    private final MetricsTypeService metricsTypeService;
    public List<MetricsDTO> getMetricsByWorker(Long workerId) {
        List<MetricsEntity> workerMetrics = metricsRepository.findAllByWorkerId(workerId).orElseThrow(() ->
                new NotFoundException(String.format("Worker with id %d", workerId)));
        return workerMetrics.stream().map((metrics) -> {
            MetricsTypeEntity metricsType = metricsTypeService.getMetricsTypeById(metrics.getMetricsTypeId());
            return new MetricsDTO(metricsType.getTypeName(), metrics.getValue());
        }).toList();

    }

}

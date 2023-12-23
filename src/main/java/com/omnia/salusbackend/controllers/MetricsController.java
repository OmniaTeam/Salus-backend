package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.dto.MetricsDTO;
import com.omnia.salusbackend.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsService metricsService;

    @GetMapping("/{worker_id}")
    public ResponseEntity<List<MetricsDTO>> getMetricsByWorkerId(@PathVariable Long worker_id) {
        return ResponseEntity.ok().body(metricsService.getMetricsByWorker(worker_id));
    }
}

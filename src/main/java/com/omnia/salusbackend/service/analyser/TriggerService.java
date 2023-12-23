package com.omnia.salusbackend.service.analyser;

import com.omnia.salusbackend.service.MetricsService;
import com.omnia.salusbackend.service.analyser.CSV.CSVComponent;
import com.omnia.salusbackend.service.analyser.CSV.CSVDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TriggerService {

    final private CSVComponent csvComponent;
    final private MetricsService metricsService;

    public void update(String file_path) throws IOException {
        var list = csvComponent.parseCsv(file_path);
        AnalyserInterface analyser = new AnalyserExample();
        for (var i : list){
            analyser.calculateRating(i);
        }

    }



}

package com.omnia.salusbackend.service.analyser;

import com.omnia.salusbackend.service.analyser.CSV.CSVAbstract;

public class AnalyserExample implements AnalyserInterface{

    @Override
    public Double calculateRating(CSVAbstract csv) {
        return 0.1;
    }
}

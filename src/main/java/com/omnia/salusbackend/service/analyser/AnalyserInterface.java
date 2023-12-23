package com.omnia.salusbackend.service.analyser;

import com.omnia.salusbackend.service.analyser.CSV.CSVAbstract;

public interface AnalyserInterface {
    public Double calculateRating(CSVAbstract csv);
}

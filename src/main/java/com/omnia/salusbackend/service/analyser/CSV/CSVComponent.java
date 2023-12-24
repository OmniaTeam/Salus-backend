package com.omnia.salusbackend.service.analyser.CSV;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


@Component
public class CSVComponent {
    public List<CSVDTO> parseCsv(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            CsvToBean<CSVDTO> csvToBean = new CsvToBeanBuilder<CSVDTO>(reader)
                    .withType(CSVDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(';')
                    .build();

            return csvToBean.parse();
        }
    }
}

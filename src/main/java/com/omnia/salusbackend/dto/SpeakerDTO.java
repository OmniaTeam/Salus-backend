package com.omnia.salusbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class SpeakerDTO {
    private Long id;
    private String fio;
    private String subjectName;
    private Double rating;
    private String description;
    private Long userId;
}

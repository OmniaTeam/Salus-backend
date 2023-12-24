package com.omnia.salusbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SignWorkerMeetDTO {

    String name;
    LocalDateTime date;
    Long speakerId;
    Integer meetRange;
}

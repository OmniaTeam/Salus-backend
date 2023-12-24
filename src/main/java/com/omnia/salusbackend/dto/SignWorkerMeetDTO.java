package com.omnia.salusbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SignWorkerMeetDTO {

    LocalDateTime date;
    Long speakerId;
    Long workerId;
    Integer meetRange;
}

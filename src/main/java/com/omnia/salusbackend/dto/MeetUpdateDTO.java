package com.omnia.salusbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MeetUpdateDTO {
    Long meet_id;
    String meet_name;
    String subject;
    LocalDateTime date;
    String link;
    String platform;

}

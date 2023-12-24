package com.omnia.salusbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class SighAdminMeetDTO {
    String meet_name;
    Long subject;
    String speaker_name;
    LocalDateTime date;
    String link;
    String platform;


}

package com.omnia.salusbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MeetDTO {
    Long meet_id;
    String meet_name;
    String meet_desc;
    String speaker_name;
    String subject;

}

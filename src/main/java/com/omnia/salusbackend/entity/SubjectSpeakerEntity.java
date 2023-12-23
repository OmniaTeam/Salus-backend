package com.omnia.salusbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SubjectSpeakerEntity {
    @Id
    private Long subjectId;
    @Id
    private Long speakerId;
}

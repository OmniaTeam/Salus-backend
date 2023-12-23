package com.omnia.salusbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Entity
@Data
@Slf4j
@NoArgsConstructor
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long meetId;
    private String text;
    private LocalDateTime time;

    public QuestionEntity(Long meetId, String text) {
        this.meetId = meetId;
        this.text = text;
        this.time = LocalDateTime.now();
    }
}

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
@NoArgsConstructor
@Slf4j
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long meetArcId;
    private String text;
    private LocalDateTime time;

    public CommentEntity(Long meetArcId, String text) {
        this.meetArcId = meetArcId;
        this.text = text;
        this.time = LocalDateTime.now();
    }
}

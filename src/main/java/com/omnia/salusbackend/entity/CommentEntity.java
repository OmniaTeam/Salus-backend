package com.omnia.salusbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long meetArchiveid;
    private String text;
    private LocalDateTime time;

    public CommentEntity(Long meetArchiveid, String text) {
        this.meetArchiveid = meetArchiveid;
        this.text = text;
        this.time = LocalDateTime.now();
    }
}

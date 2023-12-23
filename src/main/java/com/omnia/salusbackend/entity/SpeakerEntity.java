package com.omnia.salusbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SpeakerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rating;
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;


    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "speaker")
    private List<MeetEntity> meets;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "speaker_subject",
            joinColumns = @JoinColumn(name = "speaker_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<SubjectEntity> subjects = new ArrayList<>();
}

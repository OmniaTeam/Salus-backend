package com.omnia.salusbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class MeetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    @Enumerated
    private EMeetType type;
    private String connect_type;
    private String connect_link;


    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private SubjectEntity subject;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "meets")
    private List<SpeakerEntity> speaker = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "meets")
    private List<WorkerEntity> workers = new ArrayList<>();
}

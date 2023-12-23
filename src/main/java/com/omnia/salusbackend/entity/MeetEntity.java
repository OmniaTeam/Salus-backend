package com.omnia.salusbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "subject_id")
    private Long subject_id;


    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonIgnore
    private SubjectEntity subject;

    @ManyToOne
    @JoinColumn(name = "speaker_id", nullable = false)
    @JsonIgnore
    private SpeakerEntity speaker;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "meets")
    @JsonIgnore
    private List<WorkerEntity> workers = new ArrayList<>();
}

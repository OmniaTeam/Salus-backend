package com.omnia.salusbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class MeetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long speakerId;
    private Long subjectId;
    private LocalDateTime date;
    @Enumerated
    private EMeetType type;
    private String connectType;
    private String connectLink;


    //TODO: снести ебало
//    @ManyToOne
//    @JoinColumn(name = "subject_id", nullable = false)
//    @JsonIgnore
//    private SubjectEntity subject;
//
//    @ManyToOne
//    @JoinColumn(name = "speaker_id", nullable = false)
//    @JsonIgnore
//    private SpeakerEntity speaker;
//
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "meets")
//    @JsonIgnore
//    private List<WorkerEntity> workers = new ArrayList<>();
}

package com.omnia.salusbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private List<MeetEntity> meets = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjects")
    private List<SpeakerEntity> speakers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "metrics_type_id", nullable = false)
    private MetricsTypeEntity metricsType;

}

package com.omnia.salusbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class MetricsTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeName;


    @OneToMany(mappedBy = "metricsType")
    private List<MetricsEntity> metrics = new ArrayList<>();

    @OneToMany(mappedBy = "metricsType")
    private List<SubjectEntity> subjects = new ArrayList<>();

}

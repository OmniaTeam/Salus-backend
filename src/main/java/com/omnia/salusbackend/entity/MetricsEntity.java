package com.omnia.salusbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MetricsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer value;

    //TODO:снести ебало

//    @ManyToOne
//    @JoinColumn(name = "worker_id", nullable = false)
//    private WorkerEntity worker;
//
//    @ManyToOne
//    @JoinColumn(name = "metrics_type_id", nullable = false)
//    private MetricsTypeEntity metricsType;

}

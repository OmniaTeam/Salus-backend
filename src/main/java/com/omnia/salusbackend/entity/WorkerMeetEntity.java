package com.omnia.salusbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class WorkerMeetEntity {
    @Id
    private Long meetId;
    @Id
    private Long workerId;
}

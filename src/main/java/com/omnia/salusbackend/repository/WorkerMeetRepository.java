package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.WorkerMeetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerMeetRepository extends JpaRepository<WorkerMeetEntity, Long> {

    List<WorkerMeetEntity> findAllByMeetId(Long meetId);

    List<WorkerMeetEntity> findAllByWorkerId(Long workerId);
}

package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.UserEntity;
import com.omnia.salusbackend.entity.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<WorkerEntity, Long> {
    //Optional<WorkerEntity> findByUser(UserEntity user);
    Optional<WorkerEntity> findByUserId(Long userId);
}

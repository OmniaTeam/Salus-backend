package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.MetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetricsRepository extends JpaRepository<MetricsEntity, Long> {
    Optional<List<MetricsEntity>> findAllByWorkerId(Long workerId);
}

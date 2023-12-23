package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Long> {

    Optional<PlanEntity> findBySpeakerId(Long speakerId);

    List<PlanEntity> findAllBySpeakerId(Long speakerId);
}

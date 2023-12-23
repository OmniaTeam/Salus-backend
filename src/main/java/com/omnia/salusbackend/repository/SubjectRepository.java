package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    List<SubjectEntity> findAllByMetricsTypeId(Long metricsTypeId);
}

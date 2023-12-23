package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.MetricsTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsTypeRepository extends JpaRepository<MetricsTypeEntity, Long> {

}

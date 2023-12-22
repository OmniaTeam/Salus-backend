package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.MeetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetRepository extends JpaRepository<MeetEntity, Long> {
}

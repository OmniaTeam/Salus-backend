package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.EMeetType;
import com.omnia.salusbackend.entity.MeetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeetRepository extends JpaRepository<MeetEntity, Long> {
    List<MeetEntity> findAllByTypeAndDateBetweenAndSpeakerId(EMeetType type, LocalDateTime date, LocalDateTime date2, Long speakerId);
}

package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.SpeakerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeakerRepository extends JpaRepository<SpeakerEntity, Long> {
    SpeakerEntity findTopBySubjectIdOrderByRatingDesc(Long subjectId);
    Optional<SpeakerEntity> findByUserId(Long userId);
}

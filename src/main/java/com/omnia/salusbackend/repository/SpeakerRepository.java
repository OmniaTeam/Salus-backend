package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.SpeakerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends JpaRepository<SpeakerEntity, Long> {
    SpeakerEntity findTopBySubjectIdOrderByRatingDesc(Long subjectId);
}

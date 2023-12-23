package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.SubjectSpeakerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectSpeakerRepository extends JpaRepository<SubjectSpeakerEntity, Long> {
}

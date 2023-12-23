package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.SubjectSpeakerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectSpeakerRepository extends JpaRepository<SubjectSpeakerEntity, Long> {

    List<SubjectSpeakerEntity> findAllBySpeakerId(Long SpeakerId);

    List<SubjectSpeakerEntity> findAllBySubjectId(Long SubjectId);
}

package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {

    List<QuestionEntity> findAllByMeetId(Long meetId);

}

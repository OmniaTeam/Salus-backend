package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestEntity,Long> {

    List<TestEntity> findAllByMeetId(Long meetId);
}

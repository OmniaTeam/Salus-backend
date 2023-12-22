package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.MeetArchiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetArchiveRepository extends JpaRepository<MeetArchiveEntity, Long> {

}

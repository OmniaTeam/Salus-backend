package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.MeetArchiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetArchiveRepository extends JpaRepository<MeetArchiveEntity, Long> {

    Optional<MeetArchiveEntity> findByMeetId(Long meetId);
}

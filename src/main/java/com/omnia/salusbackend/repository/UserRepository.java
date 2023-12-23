package com.omnia.salusbackend.repository;

import com.omnia.salusbackend.entity.ERole;
import com.omnia.salusbackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    List<UserEntity> findByRoleIsNotIn(Collection<ERole> role);
}

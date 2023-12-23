package com.omnia.salusbackend.service;

import com.omnia.salusbackend.ecxeptions.NotFoundException;
import com.omnia.salusbackend.entity.ERole;
import com.omnia.salusbackend.entity.UserEntity;
import com.omnia.salusbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    final private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepo.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return user;
    }

    public UserEntity getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserEntity getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public List<UserEntity> getUsersForAdmin(List<ERole> roles) {
        return userRepo.findByRoleIsNotIn(roles);
    }
}

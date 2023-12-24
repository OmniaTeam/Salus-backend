package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.dto.SignWorkerMeetDTO;
import com.omnia.salusbackend.entity.ERole;
import com.omnia.salusbackend.entity.UserEntity;
import com.omnia.salusbackend.repository.UserRepository;
import com.omnia.salusbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final UserRepository userRepository;
    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUsers() {
        var roles = List.of(ERole.ADMIN);
        return ResponseEntity.ok().body(userService.getUsersForAdmin(roles));
    }

    @GetMapping("/changerole/{user_id}")
    public ResponseEntity<List<UserEntity>> changeRole(@RequestParam ERole role, @PathVariable Long user_id) {
        UserEntity user = userService.getUserById(user_id);
        user.setRole(role);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/meet/create")
//    public ResponseEntity<List<UserEntity>> createMeet(@RequestBody SignWorkerMeetDTO meet) {
//
//    }
}

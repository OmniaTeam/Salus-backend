package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.dto.SignWorkerMeetDTO;
import com.omnia.salusbackend.entity.*;
import com.omnia.salusbackend.repository.PlanRepository;
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
    private final PlanRepository planRepository;
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
//    public ResponseEntity<?> createMeet(@RequestBody SignWorkerMeetDTO meet) {
//
//        planService.checkSpeakerScheduler(signup.getSpeakerId(), signup.getDate(), signup.getMeetRange());
//        SpeakerEntity speaker = speakerRepository.findById(signup.getSpeakerId()).orElseThrow();
//        MeetEntity meet = new MeetEntity();
//        meet.setSpeaker_name(signup.getSpeakerId());
//        meet.setSubject(speaker.getSubjectId());
//        meet.setDate(signup.getDate());
//        meet.setType(EMeetType.MEETUP);
//        PlanEntity planEntity = new PlanEntity();
//        planEntity.setTime(signup.getDate());
//        planEntity.setRange(signup.getMeetRange());
//        planEntity.setSpeakerId(signup.getSpeakerId());
//        return ResponseEntity.ok().body(meet);
//    }
}

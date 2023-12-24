package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.dto.SighAdminMeetDTO;
import com.omnia.salusbackend.dto.SignWorkerMeetDTO;
import com.omnia.salusbackend.entity.*;
import com.omnia.salusbackend.repository.MeetRepository;
import com.omnia.salusbackend.repository.PlanRepository;
import com.omnia.salusbackend.repository.SpeakerRepository;
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
    private final SpeakerRepository speakerRepository;
    private final UserRepository userRepository;
    private final MeetRepository meetRepository;
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

    @PostMapping("/meet/create")
    public ResponseEntity<?> createMeet(@RequestBody SighAdminMeetDTO meet) {
        UserEntity user = userRepository.findByFio(meet.getSpeaker_name()).orElseThrow();

        SpeakerEntity speaker = speakerRepository.findByUserId(user.getId()).orElseThrow();
        MeetEntity new_meet = new MeetEntity();
        new_meet.setName(meet.getMeet_name());
        new_meet.setSpeakerId(speaker.getId());
        new_meet.setSubjectId(meet.getSubject());
        new_meet.setDate(meet.getDate());
        new_meet.setConnectType(meet.getPlatform());
        new_meet.setConnectLink(meet.getLink());

        new_meet.setType(EMeetType.LECTURE);
        meetRepository.save(new_meet);


        PlanEntity planEntity = new PlanEntity();
        planEntity.setSpeakerId(speaker.getId());
        planEntity.setRange(60);
        planEntity.setTime(meet.getDate());
        planEntity.setMeetId(new_meet.getId());

        planRepository.save(planEntity);
        return ResponseEntity.ok().build();
    }
}

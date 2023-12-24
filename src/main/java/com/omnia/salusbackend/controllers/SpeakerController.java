package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.dto.MeetDTO;
import com.omnia.salusbackend.dto.SpeakerDTO;
import com.omnia.salusbackend.entity.*;
import com.omnia.salusbackend.repository.SpeakerRepository;
import com.omnia.salusbackend.service.MeetService;
import com.omnia.salusbackend.service.SubjectService;
import com.omnia.salusbackend.service.SubjectSpeakerService;
import com.omnia.salusbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/speaker")
@RequiredArgsConstructor
public class SpeakerController {

    private final SpeakerRepository speakerRepository;
    final private SubjectSpeakerService subjectSpeakerService;
    final private MeetService meetService;
    final private UserService userService;
    final private SubjectService subjectService;


    @GetMapping
    ResponseEntity<SpeakerEntity> getSpeaker(Authentication authentication){
        UserEntity user = ((UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(speakerRepository.findByUserId(user.getId()).orElseThrow());
    }
    @GetMapping("/all")
    public ResponseEntity<List<SpeakerDTO>> getAllSpeaker(){
        List<SpeakerDTO> speakerDTOS = new ArrayList<>();
        for (var speaker :
                speakerRepository.findAll()) {
            UserEntity user = userService.getUserById(speaker.getUserId());
            SubjectEntity subject = subjectService.getWithId(speaker.getSubjectId());
            speakerDTOS.add(new SpeakerDTO(
                    speaker.getId(),
                    user.getFio(),
                    subject.getName(),
                    speaker.getRating(),
                    speaker.getDescription()

            ));
        }
        return ResponseEntity.ok().body(speakerDTOS);
    }

    @GetMapping("/{speakerId}")
    public ResponseEntity<SpeakerEntity> getSpeaker(@PathVariable Long speakerId){
        return ResponseEntity.ok().body(speakerRepository.findById(speakerId).orElseThrow());
    }
    @GetMapping("/{speakerId}/allSubject")
    public ResponseEntity<?> getAllSpeaker(@PathVariable Long speakerId){
        return ResponseEntity.ok().body(subjectSpeakerService.getAllwithSpeakerId(speakerId));
    }

    @GetMapping("/{speakerId}/meets/{date}")
    public ResponseEntity<List<MeetDTO>> getSpeakerMeets(@PathVariable Long speakerId, @PathVariable LocalDate date){
        return ResponseEntity.ok().body(meetService.getMeetsMeetsForSpeakerByDate(speakerId, date));
    }

    @GetMapping("/{speakerId}/lecture/{date}")
    public ResponseEntity<List<MeetDTO>> getSpeakerLecture(@PathVariable Long speakerId, @PathVariable LocalDate date){
        return ResponseEntity.ok().body(meetService.getMeetsLectureForSpeakerByDate(speakerId, date));
    }

    }
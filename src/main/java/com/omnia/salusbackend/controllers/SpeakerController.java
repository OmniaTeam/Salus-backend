package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.repository.SpeakerRepository;
import com.omnia.salusbackend.service.MeetService;
import com.omnia.salusbackend.service.SubjectSpeakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/speaker")
@RequiredArgsConstructor
public class SpeakerController {

    private final SpeakerRepository speakerRepository;
    final private SubjectSpeakerService subjectSpeakerService;
    final private MeetService meetService;

    @GetMapping("/{speakerId}/allSubject")
    public ResponseEntity<?> getAllSpeaker(@PathVariable Long speakerId){
        return ResponseEntity.ok().body(subjectSpeakerService.getAllwithSpeakerId(speakerId));
    }

    @GetMapping("/{speakerId}/meets/{date}")
    public ResponseEntity<?> getSpeakerMeets(@PathVariable Long speakerId, @PathVariable LocalDate date){
        return ResponseEntity.ok().body(meetService.getMeetsMeetsForSpeakerByDate(speakerId, date));
    }

    @GetMapping("/{speakerId}/lecture/{date}")
    public ResponseEntity<?> getSpeakerLecture(@PathVariable Long speakerId, @PathVariable LocalDate date){
        return ResponseEntity.ok().body(meetService.getMeetsLectureForSpeakerByDate(speakerId, date));
    }

    }
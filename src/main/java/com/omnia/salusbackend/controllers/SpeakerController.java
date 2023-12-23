package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.repository.SpeakerRepository;
import com.omnia.salusbackend.service.SubjectSpeakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/speaker")
@RequiredArgsConstructor
public class SpeakerController {

    private final SpeakerRepository speakerRepository;
    final private SubjectSpeakerService subjectSpeakerService;

    @GetMapping("/{speakerId}/allSubject")
    public ResponseEntity<?> getAllSpeaker(@PathVariable Long speakerId){
        return ResponseEntity.ok().body(subjectSpeakerService.getAllwithSpeakerId(speakerId));
    }

    }
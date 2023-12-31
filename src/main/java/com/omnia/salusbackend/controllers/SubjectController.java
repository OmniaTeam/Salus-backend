package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.entity.SubjectEntity;
import com.omnia.salusbackend.repository.SubjectRepository;
import com.omnia.salusbackend.service.SubjectSpeakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectRepository subjectRepository;
    final private SubjectSpeakerService subjectSpeakerService;

    @GetMapping("/all")
    public ResponseEntity<?> getSubjects() {
        return ResponseEntity.ok().body(subjectRepository.findAll());
    }

    @GetMapping("/{subjectId}/allSpeaker")
    public ResponseEntity<?> getAllSpeaker(@PathVariable Long subjectId){
        return ResponseEntity.ok().body(subjectSpeakerService.getAllwithSubjectId(subjectId));
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectEntity> getSubject(@PathVariable Long subjectId){
        return ResponseEntity.ok().body(subjectRepository.findById(subjectId).orElseThrow());
    }
}

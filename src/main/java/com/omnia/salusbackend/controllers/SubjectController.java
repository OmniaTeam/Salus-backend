package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectRepository subjectRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getSubjects() {
        return ResponseEntity.ok().body(subjectRepository.findAll());
    }


}

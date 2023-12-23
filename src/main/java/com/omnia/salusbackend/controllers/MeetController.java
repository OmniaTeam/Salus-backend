package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.service.MeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/meet")
@RequiredArgsConstructor
public class MeetController {
    final private MeetService meetService;

    @GetMapping("/{meetId}")
    public ResponseEntity<?> getWithID(Long meetId){
        return ResponseEntity.ok().body(meetService.getWithId(meetId));
    }






}
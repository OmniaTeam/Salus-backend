package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.service.MeetService;
import com.omnia.salusbackend.service.WorkerMeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/meet")
@RequiredArgsConstructor
public class MeetController {
    final private MeetService meetService;
    final private WorkerMeetService workerMeetService;

    @GetMapping("/{meetId}")
    public ResponseEntity<?> getWithID(Long meetId){
        return ResponseEntity.ok().body(meetService.getWithId(meetId));
    }

    @GetMapping("/{meetId}/allworker")
    public ResponseEntity<?> getall(@PathVariable Long meetId){
        return ResponseEntity.ok().body(workerMeetService.getAllwithMeetId(meetId));
    }




}
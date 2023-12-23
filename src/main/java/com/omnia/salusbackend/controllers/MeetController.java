package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.entity.EMeetType;
import com.omnia.salusbackend.service.MeetService;
import com.omnia.salusbackend.service.WorkerMeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.UnionType;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/meet")
@RequiredArgsConstructor
public class MeetController {
    final private MeetService meetService;
    final private WorkerMeetService workerMeetService;

    @GetMapping("/{meetId}/")//TODO FIX качает файл
    public ResponseEntity<?> getWithID(@PathVariable Long meetId){
        return ResponseEntity.ok().body(meetService.getWithId(meetId));
    }

    @GetMapping("/{meetId}/allworker")
    public ResponseEntity<?> getall(@PathVariable Long meetId){
        return ResponseEntity.ok().body(workerMeetService.getAllwithMeetId(meetId));
    }


    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Map<String, Object> data){
        meetService.update(data);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/lecture/all")
    public ResponseEntity<?> getallLecturewithdate(@RequestParam ("date") LocalDateTime date){
        return ResponseEntity.ok().body(meetService.getwithtypeanddate(date, EMeetType.LECTURE));
    }

}
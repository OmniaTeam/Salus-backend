package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.dto.MeetDTO;
import com.omnia.salusbackend.dto.MeetUpdateDTO;
import com.omnia.salusbackend.entity.EMeetType;
import com.omnia.salusbackend.service.MeetService;
import com.omnia.salusbackend.service.WorkerMeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.UnionType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/meet")
@RequiredArgsConstructor
public class MeetController {
    final private MeetService meetService;
    final private WorkerMeetService workerMeetService;

    @GetMapping("/{meetId}")//TODO FIX качает файл
    public ResponseEntity<MeetDTO> getWithID(@PathVariable Long meetId){
        return ResponseEntity.ok().body(meetService.getMeetDTO(meetService.getWithId(meetId)));
    }
    // TODO: refactor
    @GetMapping("/{meetId}/allworker")
    public ResponseEntity<?> getall(@PathVariable Long meetId){
        return ResponseEntity.ok().body(workerMeetService.getAllwithMeetId(meetId));
    }


    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody MeetUpdateDTO data){
        meetService.update(data);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/lecture/all")
    public ResponseEntity<List<MeetDTO>> getallLecturewithdate(@RequestParam ("date") LocalDate date){
        return ResponseEntity.ok().body(meetService.getwithtypeanddate(date, EMeetType.LECTURE));
    }

    @GetMapping("/{meet_id}/delete")
    public ResponseEntity<?> deleteMeet(@PathVariable Long meet_id){
        meetService.deleteWithid(meet_id);
        return ResponseEntity.ok().build();
    }

}
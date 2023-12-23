package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.dto.MeetsDTO;
import com.omnia.salusbackend.dto.MetricsDTO;
import com.omnia.salusbackend.entity.MeetEntity;
import com.omnia.salusbackend.entity.UserEntity;
import com.omnia.salusbackend.entity.WorkerEntity;
import com.omnia.salusbackend.service.WorkerMeetService;
import com.omnia.salusbackend.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/worker")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;
    private final WorkerMeetService workerMeetService;

    @GetMapping
    ResponseEntity<WorkerEntity> getWorker(Authentication authentication){
        UserEntity user = ((UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(workerService.getWorkerByUser(user));
    }

    @GetMapping("/{workerId}/allmeet")
    public ResponseEntity<?> getAll(@PathVariable Long workerId){
        return ResponseEntity.ok().body(workerMeetService.getAllwithWorkerId(workerId));
    }


//    @GetMapping("/{worker_id}/metrics")
//    public ResponseEntity<List<MetricsDTO>> getWorkerMetrics(@PathVariable Long worker_id) {
//        WorkerEntity worker = workerService.getWorkerById(worker_id);
//        List<MetricsDTO> collect = worker.getMetrics().stream()
//                .map(x -> (new MetricsDTO(x.getMetricsType().getTypeName(),
//                        x.getValue())))
//                .toList();
//        return ResponseEntity.ok().body(collect);
//    }
//
//    @GetMapping("/{worker_id}/meets")
//    public ResponseEntity<List<?>> getWorkerMeets(@PathVariable Long worker_id) {
//        WorkerEntity worker = workerService.getWorkerById(worker_id);
//        List<MeetEntity> meets = worker.getMeets();
//        List<MeetsDTO> meetsDTOS = meets.stream().map(x -> new MeetsDTO(x.getId(), x.getName(), x.getDescription(), x.getSpeaker().getUser().getFio())).toList();
//        return ResponseEntity.ok().body(meets);
//
//    }
}

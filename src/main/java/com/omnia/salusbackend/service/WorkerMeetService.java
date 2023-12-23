package com.omnia.salusbackend.service;

import com.omnia.salusbackend.entity.WorkerMeetEntity;
import com.omnia.salusbackend.repository.WorkerMeetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkerMeetService {
    final private WorkerMeetRepository workerMeetRepository;


    public List<WorkerMeetEntity> getAllwithMeetId(Long meetId){
        return workerMeetRepository.findAllByMeetId(meetId);
    }

    public List<WorkerMeetEntity> getAllwithWorkerId(Long WorkerId){
        return workerMeetRepository.findAllByWorkerId(WorkerId);
    }

}

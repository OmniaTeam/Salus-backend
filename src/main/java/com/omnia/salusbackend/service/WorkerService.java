package com.omnia.salusbackend.service;

import com.omnia.salusbackend.ecxeptions.NotFoundException;
import com.omnia.salusbackend.entity.UserEntity;
import com.omnia.salusbackend.entity.WorkerEntity;
import com.omnia.salusbackend.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkerService {

    private final WorkerRepository workerRepository;

    public WorkerEntity getWorkerByUser(UserEntity user) {
        return workerRepository.findByUser(user).orElseThrow(() -> new NotFoundException("Worker"));
    }

    public WorkerEntity getWorkerById(Long worker_id) {
        return workerRepository.findById(worker_id).orElseThrow(() -> new NotFoundException(String.format("Worker with id %d", worker_id)));
    }
}

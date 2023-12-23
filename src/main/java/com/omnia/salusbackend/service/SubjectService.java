package com.omnia.salusbackend.service;

import com.omnia.salusbackend.entity.SubjectEntity;
import com.omnia.salusbackend.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectService {

    final private SubjectRepository subjectRepository;

    public SubjectEntity getWithId(Long subjectId){
        return subjectRepository.findById(subjectId).orElse(null);
    }

    public List<SubjectEntity> getAll(){
        return subjectRepository.findAll();
    }


}

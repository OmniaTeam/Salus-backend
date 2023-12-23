package com.omnia.salusbackend.service;

import com.omnia.salusbackend.entity.SubjectSpeakerEntity;
import com.omnia.salusbackend.repository.SubjectSpeakerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubjectSpeakerService {
    final private SubjectSpeakerRepository subjectSpeakerRepository;

    public List<SubjectSpeakerEntity> getAllwithSubjectId(Long subjectId){
        return subjectSpeakerRepository.findAllBySubjectId(subjectId);
    }
    public List<SubjectSpeakerEntity> getAllwithSpeakerId(Long speakerId){
        return subjectSpeakerRepository.findAllBySpeakerId(speakerId);

    }


}

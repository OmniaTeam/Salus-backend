package com.omnia.salusbackend.service;

import com.omnia.salusbackend.entity.MeetEntity;
import com.omnia.salusbackend.repository.MeetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeetService {
    private final MeetRepository meetRepository;

    public MeetEntity getWithId(Long meetId){
        return meetRepository.findById(meetId).orElse(null);
    }

    public void modifyWithId(Long meetId, MeetEntity meetEntity){
        var meet = getWithId(meetId);
    }

    public void deleteWithid(Long meetId){
        var meet = getWithId(meetId);
        meetRepository.delete(meet);
    }

    public MeetEntity add(MeetEntity meetEntity){
        return meetRepository.save(meetEntity);
    }

}

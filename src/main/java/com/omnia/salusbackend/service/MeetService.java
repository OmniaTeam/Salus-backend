package com.omnia.salusbackend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnia.salusbackend.ecxeptions.NotFoundException;
import com.omnia.salusbackend.entity.MeetEntity;
import com.omnia.salusbackend.repository.MeetRepository;
import com.omnia.salusbackend.utils.JpaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeetService {
    private final MeetRepository meetRepository;

    public MeetEntity getWithId(Long meetId) {
        return meetRepository.findById(meetId).orElse(null);
    }

    public void modifyWithId(Long meetId, MeetEntity meetEntity) {
        var meet = getWithId(meetId);
    }

    public void deleteWithid(Long meetId) {
        var meet = getWithId(meetId);
        meetRepository.delete(meet);
    }

    public MeetEntity add(MeetEntity meetEntity) {
        return meetRepository.save(meetEntity);
    }

    public void update(Map<String, Object> data) {
        JpaUtils.abstractUpdate(data, meetRepository);
    }
}
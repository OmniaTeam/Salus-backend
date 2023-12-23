package com.omnia.salusbackend.service;

import com.omnia.salusbackend.entity.MeetEntity;
import com.omnia.salusbackend.repository.MeetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetService {
    private final MeetRepository meetRepository;



}

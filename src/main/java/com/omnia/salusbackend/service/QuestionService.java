package com.omnia.salusbackend.service;

import com.omnia.salusbackend.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    final private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

}

package com.omnia.salusbackend.service;

import com.omnia.salusbackend.dto.MeetDTO;
import com.omnia.salusbackend.entity.*;
import com.omnia.salusbackend.repository.MetricsTypeRepository;
import com.omnia.salusbackend.repository.SpeakerRepository;
import com.omnia.salusbackend.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RelationService {
    final private MetricsService metricsService;
    final private MeetService meetService;
    private final MetricsTypeRepository metricsTypeRepository;
    private final SubjectRepository subjectRepository;
    private final SpeakerRepository speakerRepository;

    private ArrayList<MetricsEntity> getLastMetrics(Long workerId){
        //TODO: refactor потом :)
        var health = metricsService.getLastWithType(workerId,1L);
        var mental = metricsService.getLastWithType(workerId,2L);
        var general = metricsService.getLastWithType(workerId,3L);
        var list = new ArrayList<MetricsEntity>();
        list.add(health);
        list.add(mental);
        list.add(general);
        return list;
    }

    private void sort(ArrayList<MetricsEntity> metricsList){
        for (int i = 0; i < metricsList.size() - 1; i++) {
            for (int j = 0; j < metricsList.size() - i - 1; j++) {
                if (metricsList.get(j).getValue() > metricsList.get(j + 1).getValue()) {
                    // Обмен элементов
                    MetricsEntity temp = metricsList.get(j);
                    metricsList.set(j, metricsList.get(j + 1));
                    metricsList.set(j + 1, temp);
                }
            }
        }
    }

    public ArrayList<MeetDTO> getRealtion(Long workerId){

        var list = getLastMetrics(workerId);
        sort(list);
        ArrayList<MeetDTO> relation = new ArrayList<>();
        for(var i : list){
            MetricsTypeEntity metrics = metricsTypeRepository.findById(i.getMetricsTypeId()).orElseThrow();
            List<SubjectEntity> subjects = subjectRepository.findAllByMetricsTypeId(metrics.getId());
            for (var sub :
                    subjects) {
                SpeakerEntity speaker = speakerRepository.findTopBySubjectIdOrderByRatingDesc(sub.getId());
                List<MeetDTO> meet = meetService.getMeetsLectureForSpeakerByDate(speaker.getId(), LocalDate.now());
                relation.addAll(meet);
            }
        }
        return relation;
    }



}

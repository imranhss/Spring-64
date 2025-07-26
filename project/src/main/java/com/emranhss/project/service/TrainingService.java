package com.emranhss.project.service;

import com.emranhss.project.entity.Training;
import com.emranhss.project.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    public List<Training> getByJobSeekerId(Long jobSeekerId) {
        return trainingRepository.findByJobSeekerId(jobSeekerId);
    }

    public Training save(Training training) {
        return trainingRepository.save(training);
    }

    public void delete(Long id) {
        trainingRepository.deleteById(id);
    }

}

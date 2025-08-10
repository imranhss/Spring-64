package com.emranhss.project.service;

import com.emranhss.project.dto.ExperienceDTO;
import com.emranhss.project.dto.TrainingDTO;
import com.emranhss.project.entity.Experience;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.Training;
import com.emranhss.project.repository.JobSeekerRepository;
import com.emranhss.project.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;


    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public List<TrainingDTO> getByJobSeekerId(Long jobSeekerId) {
        List<Training> trainings = trainingRepository.findByJobSeekerId(jobSeekerId);

        return trainings.stream()
                .map(TrainingDTO::new)
                .collect(Collectors.toList());
    }

    public Training save(Training training, String email) {

        JobSeeker jobSeeker = jobSeekerRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("JobSeeker not found"));
        training.setJobSeeker(jobSeeker);
        return trainingRepository.save(training);
    }

    public void delete(Long id) {
        trainingRepository.deleteById(id);
    }

}

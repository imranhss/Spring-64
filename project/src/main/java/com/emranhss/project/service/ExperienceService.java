package com.emranhss.project.service;

import com.emranhss.project.entity.Experience;
import com.emranhss.project.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    public List<Experience> getByJobSeekerId(Long jobSeekerId) {
        return experienceRepository.findByJobSeekerId(jobSeekerId);
    }

    public Experience save(Experience experience) {
        return experienceRepository.save(experience);
    }

    public void delete(Long id) {
        experienceRepository.deleteById(id);
    }
}

package com.emranhss.project.service;

import com.emranhss.project.entity.Education;
import com.emranhss.project.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    @Autowired
    private EducationRepository educationRepository;

    public List<Education> getByJobSeekerId(Long jobSeekerId) {
        return educationRepository.findByJobSeekerId(jobSeekerId);
    }

    public Education save(Education education) {
        return educationRepository.save(education);
    }

    public void delete(Long id) {
        educationRepository.deleteById(id);
    }

}

package com.emranhss.project.service;

import com.emranhss.project.dto.EducationDTO;
import com.emranhss.project.dto.ExperienceDTO;
import com.emranhss.project.entity.Education;
import com.emranhss.project.entity.Experience;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.repository.ExperienceRepository;
import com.emranhss.project.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public List<ExperienceDTO> getByJobSeekerId(Long jobSeekerId) {
        List<Experience> experiences = experienceRepository.findByJobSeekerId(jobSeekerId);

        return experiences.stream()
                .map(ExperienceDTO::new)
                .collect(Collectors.toList());
    }

    public Experience save(Experience experience, String email) {
        JobSeeker jobSeeker = jobSeekerRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("JobSeeker not found"));
        experience.setJobSeeker(jobSeeker);
        return experienceRepository.save(experience);
    }

    public void delete(Long id) {
        experienceRepository.deleteById(id);
    }
}

package com.emranhss.project.service;

import com.emranhss.project.dto.EducationDTO;
import com.emranhss.project.entity.Education;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.repository.EducationRepository;
import com.emranhss.project.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationService {
    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public List<EducationDTO> getByJobSeekerId(Long jobSeekerId) {
        List<Education> educations = educationRepository.findByJobSeekerId(jobSeekerId);
        return educations.stream()
                .map(EducationDTO::new)
                .collect(Collectors.toList());
    }

    public Education saveEducation(Education education, String email) {

        JobSeeker jobSeeker = jobSeekerRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("JobSeeker not found"));

        education.setJobSeeker(jobSeeker);

        return educationRepository.save(education);
    }

    public void delete(Long id) {
        educationRepository.deleteById(id);
    }

}

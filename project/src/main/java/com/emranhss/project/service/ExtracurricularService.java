package com.emranhss.project.service;

import com.emranhss.project.dto.ExperienceDTO;
import com.emranhss.project.dto.ExtracurricularDTO;
import com.emranhss.project.entity.Experience;
import com.emranhss.project.entity.Extracurricular;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.repository.ExtracurricularRepository;
import com.emranhss.project.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExtracurricularService {

    @Autowired
    private ExtracurricularRepository extracurricularRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public List<ExtracurricularDTO> getByJobSeekerId(Long jobSeekerId) {
        List<Extracurricular> extracurriculars = extracurricularRepository.findByJobSeekerId(jobSeekerId);
        return extracurriculars.stream()
                .map(ExtracurricularDTO::new)
                .collect(Collectors.toList());
    }

    public Extracurricular save(Extracurricular extracurricular, String email) {
        JobSeeker jobSeeker = jobSeekerRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("JobSeeker not found"));

        extracurricular.setJobSeeker(jobSeeker);
        return extracurricularRepository.save(extracurricular);
    }

    public void delete(Long id) {
        extracurricularRepository.deleteById(id);
    }
}

package com.emranhss.project.service;

import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public List<JobSeeker> getAll() {
        return jobSeekerRepository.findAll();
    }

    public Optional<JobSeeker> getById(Long id) {
        return jobSeekerRepository.findById(id);
    }

    public JobSeeker save(JobSeeker jobSeeker) {
        return jobSeekerRepository.save(jobSeeker);
    }

    public void delete(Long id) {
        jobSeekerRepository.deleteById(id);
    }
}

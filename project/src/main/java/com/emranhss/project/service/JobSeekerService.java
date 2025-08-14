package com.emranhss.project.service;

import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.User;
import com.emranhss.project.jwt.JwtService;
import com.emranhss.project.repository.ITokenRepository;
import com.emranhss.project.repository.IUserRepo;
import com.emranhss.project.repository.JobSeekerRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public JobSeeker getProfileByUserId(int userId) {
        return jobSeekerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Job Seeker not found"));
    }




}

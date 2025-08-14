package com.emranhss.project.service;

import com.emranhss.project.entity.*;
import com.emranhss.project.jwt.JwtService;
import com.emranhss.project.repository.IEmployerRepository;
import com.emranhss.project.repository.ITokenRepository;
import com.emranhss.project.repository.IUserRepo;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployerService {


    @Autowired
    private IEmployerRepository  employerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private ITokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;


    @Value("src/main/resources/static/images")
    private String uploadDir;



    public Optional<Employer> getById(Long id) {
        return employerRepository.findById(id);
    }

    public List<Employer> getAll() {
        return employerRepository.findAll();
    }

    public Employer getProfileByUserId(int userId) {
        return employerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Job Seeker not found"));
    }









}

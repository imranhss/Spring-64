package com.emranhss.project.restcontroller;

import com.emranhss.project.dto.ExperienceDTO;
import com.emranhss.project.dto.ExtracurricularDTO;
import com.emranhss.project.entity.Experience;
import com.emranhss.project.entity.Extracurricular;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.User;
import com.emranhss.project.repository.IUserRepo;
import com.emranhss.project.service.ExperienceService;
import com.emranhss.project.service.ExtracurricularService;
import com.emranhss.project.service.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/extracurricular/")
public class ExtracurricularRestController {

    @Autowired
    private ExtracurricularService extracurricularService;

    @Autowired
    private JobSeekerService jobSeekerService;
    @Autowired
    private IUserRepo userRepo;



    @PostMapping("add")
    public ResponseEntity<Extracurricular> addExperience(@RequestBody Extracurricular extracurricular, Authentication authentication) {
        String email = authentication.getName();  // Logged-in user's email
        Extracurricular savedExtracurricular = extracurricularService.save(extracurricular, email);
        return ResponseEntity.ok(savedExtracurricular);
    }


    @GetMapping("all")
    public ResponseEntity<List<ExtracurricularDTO>> getEducationsByJobSeeker(Authentication authentication) {
        // Get logged-in user email
        String email = authentication.getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        JobSeeker jobSeeker = jobSeekerService.getProfileByUserId(user.getId());


        List<ExtracurricularDTO> extracurriculars = extracurricularService.getByJobSeekerId(jobSeeker.getId());


        return ResponseEntity.ok(extracurriculars);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        extracurricularService.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }


}

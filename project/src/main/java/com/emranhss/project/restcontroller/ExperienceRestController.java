package com.emranhss.project.restcontroller;

import com.emranhss.project.dto.EducationDTO;
import com.emranhss.project.dto.ExperienceDTO;
import com.emranhss.project.entity.Education;
import com.emranhss.project.entity.Experience;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.User;
import com.emranhss.project.repository.IUserRepo;
import com.emranhss.project.service.ExperienceService;
import com.emranhss.project.service.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experience/")
public class ExperienceRestController {
    @Autowired
    private ExperienceService  experienceService;

    @Autowired
    private JobSeekerService jobSeekerService;
    @Autowired
    private IUserRepo userRepo;


    @PostMapping("add")
    public ResponseEntity<Experience> addExperience(@RequestBody Experience experience, Authentication authentication) {
        String email = authentication.getName();  // Logged-in user's email
        Experience savedExperience = experienceService.save(experience, email);
        return ResponseEntity.ok(savedExperience);
    }


    @GetMapping("all")
    public ResponseEntity<List<ExperienceDTO>> getEducationsByJobSeeker(Authentication authentication) {
        // Get logged-in user email
        String email = authentication.getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        JobSeeker jobSeeker = jobSeekerService.getProfileByUserId(user.getId());


        List<ExperienceDTO> educations = experienceService.getByJobSeekerId(jobSeeker.getId());


        return ResponseEntity.ok(educations);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }


}

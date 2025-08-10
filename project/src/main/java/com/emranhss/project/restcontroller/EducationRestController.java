package com.emranhss.project.restcontroller;


import com.emranhss.project.dto.EducationDTO;
import com.emranhss.project.entity.Education;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.User;
import com.emranhss.project.repository.EducationRepository;
import com.emranhss.project.repository.IUserRepo;
import com.emranhss.project.service.EducationService;
import com.emranhss.project.service.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/education/")
public class EducationRestController {

    @Autowired
    private EducationService educationService;
    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private IUserRepo userRepo;

    @PostMapping("add")
    public ResponseEntity<Education> addEducation(@RequestBody Education education, Authentication authentication) {
        String email = authentication.getName();  // Logged-in user's email
        Education savedEducation = educationService.saveEducation(education, email);
        return ResponseEntity.ok(savedEducation);
    }

    @GetMapping("all")
    public ResponseEntity<List<EducationDTO>> getEducationsByJobSeeker(Authentication authentication) {
        // Get logged-in user email
        String email = authentication.getName();

        Optional<User> user =userRepo.findByEmail(email);
        JobSeeker jobSeeker = jobSeekerService.getProfileByUserId(user.get().getId());


        List<EducationDTO> educations = educationService.getByJobSeekerId(jobSeeker.getId());


        return ResponseEntity.ok(educations);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }



}

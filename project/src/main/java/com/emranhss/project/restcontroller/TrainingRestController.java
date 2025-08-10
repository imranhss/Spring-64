package com.emranhss.project.restcontroller;


import com.emranhss.project.dto.TrainingDTO;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.Training;
import com.emranhss.project.entity.User;
import com.emranhss.project.repository.IUserRepo;
import com.emranhss.project.service.JobSeekerService;
import com.emranhss.project.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/training/")
public class TrainingRestController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private IUserRepo userRepo;


    @PostMapping("add")
    public ResponseEntity<Training> addTraining(@RequestBody Training training, Authentication authentication) {
        String email = authentication.getName();  // Logged-in user's email
        Training savedTraining = trainingService.save(training, email);
        return ResponseEntity.ok(savedTraining);
    }

    @GetMapping("all")
    public ResponseEntity<List<TrainingDTO>> getTrainingsByJobSeeker(Authentication authentication) {
        String email = authentication.getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        JobSeeker jobSeeker = jobSeekerService.getProfileByUserId(user.getId());

        List<TrainingDTO> trainings = trainingService.getByJobSeekerId(jobSeeker.getId());

        return ResponseEntity.ok(trainings);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        trainingService.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

}

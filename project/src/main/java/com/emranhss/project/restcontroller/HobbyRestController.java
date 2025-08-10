package com.emranhss.project.restcontroller;

import com.emranhss.project.dto.HobbyDTO;
import com.emranhss.project.entity.Hobby;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.User;
import com.emranhss.project.repository.IUserRepo;
import com.emranhss.project.service.HobbyService;
import com.emranhss.project.service.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hobby/")
public class HobbyRestController {

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private IUserRepo userRepo;

    @PostMapping("add")
    public ResponseEntity<Hobby> addHobby(@RequestBody Hobby hobby, Authentication authentication) {
        String email = authentication.getName();  // Logged-in user's email
        Hobby savedHobby = hobbyService.save(hobby, email);
        return ResponseEntity.ok(savedHobby);
    }

    @GetMapping("all")
    public ResponseEntity<List<HobbyDTO>> getHobbiesByJobSeeker(Authentication authentication) {
        String email = authentication.getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        JobSeeker jobSeeker = jobSeekerService.getProfileByUserId(user.getId());

        List<HobbyDTO> hobbies = hobbyService.getByJobSeekerId(jobSeeker.getId());

        return ResponseEntity.ok(hobbies);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteHobby(@PathVariable Long id) {
        hobbyService.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}

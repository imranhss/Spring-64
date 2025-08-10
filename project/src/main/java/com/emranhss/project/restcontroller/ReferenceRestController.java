package com.emranhss.project.restcontroller;

import com.emranhss.project.dto.ReferenceDTO;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.Reference;
import com.emranhss.project.entity.User;
import com.emranhss.project.repository.IUserRepo;
import com.emranhss.project.service.JobSeekerService;
import com.emranhss.project.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reference/")
public class ReferenceRestController {

    @Autowired
    private ReferenceService referenceService;

    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private IUserRepo userRepo;

    @PostMapping("add")
    public ResponseEntity<Reference> addReference(@RequestBody Reference reference, Authentication authentication) {
        String email = authentication.getName();
        Reference savedReference = referenceService.save(reference, email);
        return ResponseEntity.ok(savedReference);
    }

    @GetMapping("all")
    public ResponseEntity<List<ReferenceDTO>> getReferencesByJobSeeker(Authentication authentication) {
        String email = authentication.getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        JobSeeker jobSeeker = jobSeekerService.getProfileByUserId(user.getId());

        List<ReferenceDTO> references = referenceService.getByJobSeekerId(jobSeeker.getId());

        return ResponseEntity.ok(references);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReference(@PathVariable Long id) {
        referenceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

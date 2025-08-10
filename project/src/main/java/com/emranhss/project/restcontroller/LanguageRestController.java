package com.emranhss.project.restcontroller;

import com.emranhss.project.dto.LanguageDTO;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.Language;
import com.emranhss.project.entity.User;
import com.emranhss.project.repository.IUserRepo;
import com.emranhss.project.service.JobSeekerService;
import com.emranhss.project.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/language/")
public class LanguageRestController {

    @Autowired
    private LanguageService languageService;

    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private IUserRepo userRepo;

    @PostMapping("add")
    public ResponseEntity<Language> addLanguage(@RequestBody Language language, Authentication authentication) {
        String email = authentication.getName();
        Language savedLanguage = languageService.save(language, email);
        return ResponseEntity.ok(savedLanguage);
    }

    @GetMapping("all")
    public ResponseEntity<List<LanguageDTO>> getLanguagesByJobSeeker(Authentication authentication) {
        String email = authentication.getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        JobSeeker jobSeeker = jobSeekerService.getProfileByUserId(user.getId());

        List<LanguageDTO> languages = languageService.getByJobSeekerId(jobSeeker.getId());

        return ResponseEntity.ok(languages);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Long id) {
        languageService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

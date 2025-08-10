package com.emranhss.project.restcontroller;

import com.emranhss.project.dto.SkillDTO;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.Skill;
import com.emranhss.project.entity.User;
import com.emranhss.project.repository.IUserRepo;
import com.emranhss.project.service.JobSeekerService;
import com.emranhss.project.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill/")
public class SkillRestController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private IUserRepo userRepo;

    @PostMapping("add")
    public ResponseEntity<Skill> addSkill(@RequestBody Skill skill, Authentication authentication) {
        String email = authentication.getName();
        Skill savedSkill = skillService.save(skill, email);
        return ResponseEntity.ok(savedSkill);
    }

    @GetMapping("all")
    public ResponseEntity<List<SkillDTO>> getSkillsByJobSeeker(Authentication authentication) {
        String email = authentication.getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        JobSeeker jobSeeker = jobSeekerService.getProfileByUserId(user.getId());

        List<SkillDTO> skills = skillService.getByJobSeekerId(jobSeeker.getId());

        return ResponseEntity.ok(skills);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

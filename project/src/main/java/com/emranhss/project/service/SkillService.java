package com.emranhss.project.service;

import com.emranhss.project.dto.SkillDTO;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.Skill;
import com.emranhss.project.repository.JobSeekerRepository;
import com.emranhss.project.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public List<SkillDTO> getByJobSeekerId(Long jobSeekerId) {
        List<Skill> skills = skillRepository.findByJobSeekerId(jobSeekerId);
        return skills.stream()
                .map(SkillDTO::new)
                .collect(Collectors.toList());
    }

    public Skill save(Skill skill, String email) {
        JobSeeker jobSeeker = jobSeekerRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("JobSeeker not found"));

        skill.setJobSeeker(jobSeeker);
        return skillRepository.save(skill);
    }

    public void delete(Long id) {
        skillRepository.deleteById(id);
    }
}

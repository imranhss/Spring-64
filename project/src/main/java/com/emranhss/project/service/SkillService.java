package com.emranhss.project.service;

import com.emranhss.project.entity.Skill;
import com.emranhss.project.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getByJobSeekerId(Long jobSeekerId) {
        return skillRepository.findByJobSeekerId(jobSeekerId);
    }

    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    public void delete(Long id) {
        skillRepository.deleteById(id);
    }
}

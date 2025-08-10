package com.emranhss.project.service;

import com.emranhss.project.dto.LanguageDTO;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.Language;
import com.emranhss.project.repository.JobSeekerRepository;
import com.emranhss.project.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public List<LanguageDTO> getByJobSeekerId(Long jobSeekerId) {
        List<Language> languages = languageRepository.findByJobSeekerId(jobSeekerId);
        return languages.stream()
                .map(LanguageDTO::new)
                .collect(Collectors.toList());
    }

    public Language save(Language language, String email) {
        JobSeeker jobSeeker = jobSeekerRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("JobSeeker not found"));

        language.setJobSeeker(jobSeeker);
        return languageRepository.save(language);
    }

    public void delete(Long id) {
        languageRepository.deleteById(id);
    }
}

package com.emranhss.project.service;

import com.emranhss.project.entity.Language;
import com.emranhss.project.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    public List<Language> getByJobSeekerId(Long jobSeekerId) {
        return languageRepository.findByJobSeekerId(jobSeekerId);
    }

    public Language save(Language language) {
        return languageRepository.save(language);
    }

    public void delete(Long id) {
        languageRepository.deleteById(id);
    }

}

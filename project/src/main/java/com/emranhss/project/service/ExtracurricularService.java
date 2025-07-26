package com.emranhss.project.service;

import com.emranhss.project.entity.Extracurricular;
import com.emranhss.project.repository.ExtracurricularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtracurricularService {

    @Autowired
    private ExtracurricularRepository extracurricularRepository;

    public List<Extracurricular> getByJobSeekerId(Long jobSeekerId) {
        return extracurricularRepository.findByJobSeekerId(jobSeekerId);
    }

    public Extracurricular save(Extracurricular extracurricular) {
        return extracurricularRepository.save(extracurricular);
    }

    public void delete(Long id) {
        extracurricularRepository.deleteById(id);
    }
}

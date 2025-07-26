package com.emranhss.project.service;


import com.emranhss.project.entity.Reference;
import com.emranhss.project.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceService {
    @Autowired
    private ReferenceRepository referenceRepository;

    public List<Reference> getByJobSeekerId(Long jobSeekerId) {
        return referenceRepository.findByJobSeekerId(jobSeekerId);
    }

    public Reference save(Reference reference) {
        return referenceRepository.save(reference);
    }

    public void delete(Long id) {
        referenceRepository.deleteById(id);
    }

}

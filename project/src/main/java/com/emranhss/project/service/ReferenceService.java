package com.emranhss.project.service;

import com.emranhss.project.dto.ReferenceDTO;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.Reference;
import com.emranhss.project.repository.JobSeekerRepository;
import com.emranhss.project.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReferenceService {

    @Autowired
    private ReferenceRepository referenceRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public List<ReferenceDTO> getByJobSeekerId(Long jobSeekerId) {
        List<Reference> references = referenceRepository.findByJobSeekerId(jobSeekerId);
        return references.stream()
                .map(ReferenceDTO::new)
                .collect(Collectors.toList());
    }

    public Reference save(Reference reference, String email) {
        JobSeeker jobSeeker = jobSeekerRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("JobSeeker not found"));

        reference.setJobSeeker(jobSeeker);
        return referenceRepository.save(reference);
    }

    public void delete(Long id) {
        referenceRepository.deleteById(id);
    }
}

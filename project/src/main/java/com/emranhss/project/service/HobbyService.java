package com.emranhss.project.service;

import com.emranhss.project.dto.HobbyDTO;
import com.emranhss.project.entity.Hobby;
import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.repository.HobbyRepository;
import com.emranhss.project.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HobbyService {
    @Autowired
    private HobbyRepository hobbyRepository;



    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public List<HobbyDTO> getByJobSeekerId(Long jobSeekerId) {
        List<Hobby> hobbies = hobbyRepository.findByJobSeekerId(jobSeekerId);
        return hobbies.stream()
                .map(HobbyDTO::new)
                .collect(Collectors.toList());
    }

    public Hobby save(Hobby hobby, String email) {
        JobSeeker jobSeeker = jobSeekerRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("JobSeeker not found"));

        hobby.setJobSeeker(jobSeeker);
        return hobbyRepository.save(hobby);
    }

    public void delete(Long id) {
        hobbyRepository.deleteById(id);
    }


}

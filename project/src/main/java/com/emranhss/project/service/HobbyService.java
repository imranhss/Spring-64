package com.emranhss.project.service;

import com.emranhss.project.entity.Hobby;
import com.emranhss.project.repository.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyService {
    @Autowired
    private HobbyRepository hobbyRepository;

    public List<Hobby> getByJobSeekerId(Long jobSeekerId) {
        return hobbyRepository.findByJobSeekerId(jobSeekerId);
    }

    public Hobby save(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    public void delete(Long id) {
        hobbyRepository.deleteById(id);
    }
}

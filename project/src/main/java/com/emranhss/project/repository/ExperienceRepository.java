package com.emranhss.project.repository;

import com.emranhss.project.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByJobSeekerId(Long jobSeekerId);
}

package com.emranhss.project.repository;

import com.emranhss.project.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByJobSeekerId(Long jobSeekerId);
}

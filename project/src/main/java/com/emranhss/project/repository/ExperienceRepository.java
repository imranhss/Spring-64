package com.emranhss.project.repository;

import com.emranhss.project.entity.Education;
import com.emranhss.project.entity.Experience;
import com.emranhss.project.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    List<Experience> findByJobSeekerId(Long jobSeekerId);


}

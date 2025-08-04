package com.emranhss.project.repository;

import com.emranhss.project.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

    Optional<JobSeeker> findByUserId(int userId);

}

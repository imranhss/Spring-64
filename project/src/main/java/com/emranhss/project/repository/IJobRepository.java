package com.emranhss.project.repository;

import com.emranhss.project.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJobRepository extends JpaRepository<Job, Long> {
    List<Job> findByEmployerId(Long employerId);
}

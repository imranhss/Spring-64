package com.emranhss.project.repository;

import com.emranhss.project.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByJobSeekerId(Long jobSeekerId);
}

package com.emranhss.project.repository;

import com.emranhss.project.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby,Long> {
    List<Hobby> findByJobSeekerId(Long jobSeekerId);
}

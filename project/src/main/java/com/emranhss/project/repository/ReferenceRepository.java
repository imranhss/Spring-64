package com.emranhss.project.repository;

import com.emranhss.project.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference,Long> {
    List<Reference> findByJobSeekerId(Long jobSeekerId);

}

package com.emranhss.project.repository;

import com.emranhss.project.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

    Optional<JobSeeker> findByUserId(int userId);

    @Query("SELECT js FROM JobSeeker js WHERE js.user.email = :email")
    Optional<JobSeeker> findByUserEmail(@Param("email") String email);

    @Query("SELECT j FROM JobSeeker j LEFT JOIN FETCH j.educations WHERE j.id = :id")
    Optional<JobSeeker> findByIdWithEducations(@Param("id") Long id);

}

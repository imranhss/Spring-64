package com.emranhss.project.repository;

import com.emranhss.project.entity.Employer;
import com.emranhss.project.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployerRepository extends JpaRepository<Employer,Long> {

    Optional<Employer> findByUserId(int userId);

    @Query("SELECT em FROM Employer em WHERE em.user.email = :email")
    Optional<Employer> findByUserEmail(@Param("email") String email);




}

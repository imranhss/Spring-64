package com.emranhss.project.repository;

import com.emranhss.project.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDivisionRepo extends JpaRepository<Division, Integer> {

}

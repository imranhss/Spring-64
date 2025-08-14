package com.emranhss.project.repository;

import com.emranhss.project.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDivisionRepo extends JpaRepository<Division, Integer> {

    // IDivisionRepo
    List<Division> findByCountryId(Integer id);
}

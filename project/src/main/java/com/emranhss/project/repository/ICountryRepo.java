package com.emranhss.project.repository;

import com.emranhss.project.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepo extends JpaRepository<Country, Long> {
}

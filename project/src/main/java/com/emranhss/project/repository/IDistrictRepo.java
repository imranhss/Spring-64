package com.emranhss.project.repository;

import com.emranhss.project.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDistrictRepo extends JpaRepository<District, Integer> {

    public District findByName(String name);

}

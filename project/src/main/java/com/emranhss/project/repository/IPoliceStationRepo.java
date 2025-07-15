package com.emranhss.project.repository;

import com.emranhss.project.entity.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPoliceStationRepo extends JpaRepository<PoliceStation, Integer> {

}

package com.emranhss.project.dto;

import com.emranhss.project.entity.District;

import java.util.List;

public class PoliceStationResponseDTO {

    private int id;
    private String name;
    private DistrictResponseDTO district;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DistrictResponseDTO getDistrict() {
        return district;
    }

    public void setDistrict(DistrictResponseDTO district) {
        this.district = district;
    }
}

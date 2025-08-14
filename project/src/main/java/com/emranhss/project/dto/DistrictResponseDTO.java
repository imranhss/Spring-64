package com.emranhss.project.dto;

import java.util.List;

public class DistrictResponseDTO {

    private int id;
    private String name;
    private DivisionResponseDTO division;


    public DistrictResponseDTO() {
    }

    public DistrictResponseDTO(int id, String name, DivisionResponseDTO division) {
        this.id = id;
        this.name = name;
        this.division = division;
    }

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

    public DivisionResponseDTO getDivision() {
        return division;
    }

    public void setDivision(DivisionResponseDTO division) {
        this.division = division;
    }
}

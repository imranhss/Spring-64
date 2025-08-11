package com.emranhss.project.dto;

import java.util.List;

public class DivisionResponseDTO {


    private int id;
    private String name;
    private CountryResponseDTO country; // nested DTO with id and name // Just District IDs

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

    public CountryResponseDTO getCountry() {
        return country;
    }

    public void setCountry(CountryResponseDTO country) {
        this.country = country;
    }
}

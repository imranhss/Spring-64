package com.emranhss.project.dto;

import java.util.List;

public class CountryResponseDTO {
    private int id;
    private String name;
    private List<Integer> divisions;

    // Getters & setters

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

    public List<Integer> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Integer> divisions) {
        this.divisions = divisions;
    }
}
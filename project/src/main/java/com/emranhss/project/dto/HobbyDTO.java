package com.emranhss.project.dto;

import com.emranhss.project.entity.Hobby;

public class HobbyDTO {

    private Long id;
    private String name;

    // Constructor mapping from entity
    public HobbyDTO(Hobby hobby) {
        this.id = hobby.getId();
        this.name = hobby.getName();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

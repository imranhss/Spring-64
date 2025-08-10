package com.emranhss.project.dto;

import com.emranhss.project.entity.Reference;

public class ReferenceDTO {

    private Long id;
    private String name;
    private String contact;
    private String relation;

    // Constructor to map from entity
    public ReferenceDTO(Reference reference) {
        this.id = reference.getId();
        this.name = reference.getName();
        this.contact = reference.getContact();
        this.relation = reference.getRelation();
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}

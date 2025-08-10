package com.emranhss.project.dto;

import com.emranhss.project.entity.Experience;

import java.util.Date;

public class ExperienceDTO {


    private Long id;
    private String company;
    private String position;
    private Date fromDate;
    private Date toDate;
    private String description;

    // Constructor mapping from entity


    public ExperienceDTO(Experience experience) {
        this.id = experience.getId();
        this.company = experience.getCompany();
        this.position = experience.getPosition();
        this.fromDate = experience.getFromDate();
        this.toDate = experience.getToDate();
        this.description = experience.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

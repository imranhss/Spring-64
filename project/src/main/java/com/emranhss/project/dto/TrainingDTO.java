package com.emranhss.project.dto;

import com.emranhss.project.entity.Training;

public class TrainingDTO {

    private Long id;
    private String title;
    private String institute;
    private String duration;
    private String description;

    // Constructor mapping from entity
    public TrainingDTO(Training training) {
        this.id = training.getId();
        this.title = training.getTitle();
        this.institute = training.getInstitute();
        this.duration = training.getDuration();
        this.description = training.getDescription();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

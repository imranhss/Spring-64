package com.emranhss.project.dto;

import com.emranhss.project.entity.JobSeeker;

import java.util.Date;

public class JobSeekerDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String address;
    private Date dateOfBirth;
    private String photo;

    // Constructor
    public JobSeekerDTO(JobSeeker jobSeeker) {
        this.id = jobSeeker.getId();
        this.name = jobSeeker.getName();
        this.email = jobSeeker.getEmail();
        this.phone = jobSeeker.getPhone();
        this.gender = jobSeeker.getGender();
        this.address = jobSeeker.getAddress();
        this.dateOfBirth = jobSeeker.getDateOfBirth();
        this.photo = jobSeeker.getPhoto();
    }
}

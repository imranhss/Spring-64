package com.emranhss.project.entity;


import jakarta.persistence.*;

@Entity
public class Reference {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contact;
    private String relation;

    @ManyToOne
    private JobSeeker jobSeeker;

}

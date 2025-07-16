package com.emranhss.project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Division> divisions;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Division> getDivisions() { return divisions; }
    public void setDivisions(List<Division> divisions) { this.divisions = divisions; }
}

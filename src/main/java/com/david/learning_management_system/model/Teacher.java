package com.david.learning_management_system.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "teacher")
    private List <Course> courses;

    @OneToMany(mappedBy = "teacher")
    private List<Schedule> schedules = new ArrayList<>();
}
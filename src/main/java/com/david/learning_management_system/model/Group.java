package com.david.learning_management_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "groups")

public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @ManyToMany(mappedBy = "groups")
    private List<Student> students;

    @OneToMany(mappedBy = "group")
    private List <Schedule> schedule;

    @ManyToMany(mappedBy = "groups")
    private List<Course> courses;
}
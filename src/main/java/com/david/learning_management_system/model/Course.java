package com.david.learning_management_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "course")
@SQLDelete(sql = "UPDATE course SET deleted = true WHERE id =?")
@SQLRestriction("deleted = false")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "course")
    private List<Schedule> schedules;

    private boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "course")
    private List<CourseGroup> courseGroups = new ArrayList<>();
}
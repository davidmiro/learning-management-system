package com.david.learning_management_system.repository;

import com.david.learning_management_system.Exception.CourseNotFoundException;
import com.david.learning_management_system.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    default Course findCourseByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }

    default void deleteCourseByIdOrThrow(Long id) {
        Course course = findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
        delete(course);
    }

}

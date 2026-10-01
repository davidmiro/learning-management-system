package com.david.learning_management_system.repository;

import com.david.learning_management_system.model.CourseGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseGroupRepository extends JpaRepository<CourseGroup, Long> {

    boolean existsByCourseIdAndGroupId(Long courseId, Long groupId);

    Optional<CourseGroup> findByCourseIdAndGroupId(Long courseId, Long groupId);
}
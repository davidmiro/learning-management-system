package com.david.learning_management_system.repository;

import com.david.learning_management_system.Exception.TeacherNotFoundException;
import com.david.learning_management_system.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    default Teacher findTeacherByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));
    }

    default void deleteTeacherByIdOrThrow(Long id) {
        Teacher teacher = findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + id));
        delete(teacher);

    }
}
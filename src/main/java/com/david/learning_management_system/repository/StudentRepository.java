package com.david.learning_management_system.repository;

import com.david.learning_management_system.Exception.StudentNotFoundException;
import com.david.learning_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    default Student findStudentByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    default void deleteStudentByIdOrThrow(Long id) {
        Student student = findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        delete(student);
    }
}

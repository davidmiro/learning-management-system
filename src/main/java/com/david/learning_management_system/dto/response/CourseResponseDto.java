package com.david.learning_management_system.dto.response;

public record CourseResponseDto(
        Long id,
        String courseName,
        String description,
        TeacherResponseDto teacherId
) {
}

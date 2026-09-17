package com.david.learning_management_system.dto.request;

public record CourseUpdateDto(
        String courseName,
        String description,
        Long teacherId
) {
}

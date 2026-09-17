package com.david.learning_management_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseCreateDto(
        @NotBlank String courseName,
        String description,
        @NotNull Long teacherId
) {
}

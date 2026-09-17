package com.david.learning_management_system.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TeacherCreateDto(
        @NotBlank String firstName,
        @NotBlank String lastName
) {
}

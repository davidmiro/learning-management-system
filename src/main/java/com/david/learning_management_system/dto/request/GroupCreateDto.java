package com.david.learning_management_system.dto.request;

import jakarta.validation.constraints.NotBlank;

public record GroupCreateDto(
        @NotBlank(message = "Group name is required")
        String groupName
) {
}

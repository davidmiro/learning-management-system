package com.david.learning_management_system.dto.request;

import jakarta.validation.constraints.NotBlank;

public record GroupUpdateDto(
        @NotBlank String groupName
) {
}

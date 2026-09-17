package com.david.learning_management_system.dto.response;

import java.util.Set;

public record StudentResponseDto(
        Long id,
        String firstName,
        String lastName,
        Set<GroupResponseDto> groups
) {
}

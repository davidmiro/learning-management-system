package com.david.learning_management_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record StudentCreateDto (
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotEmpty Set<Long> groupIds

){}

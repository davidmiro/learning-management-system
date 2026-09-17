package com.david.learning_management_system.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record ScheduleCreateDto(
        @NotNull Long groupId,
        @NotNull Long courseId,
        @NotNull LocalDateTime startDateTime,
        @NotNull LocalDateTime endDateTime
) {
}

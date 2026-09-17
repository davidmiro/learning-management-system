package com.david.learning_management_system.dto.request;

import java.time.LocalDateTime;

public record ScheduleUpdateDto(
        LocalDateTime startDateTime,
        LocalDateTime endDateTime) {
}

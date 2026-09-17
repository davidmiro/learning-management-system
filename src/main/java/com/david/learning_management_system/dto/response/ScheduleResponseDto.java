package com.david.learning_management_system.dto.response;

import java.time.LocalDateTime;

public record ScheduleResponseDto(
        Long id,
        Long groupId,
        String groupName,
        Long courseId,
        String courseName,
        Long teacherId,
        String teacherFirstName,
        String teacherLastName,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) {
}

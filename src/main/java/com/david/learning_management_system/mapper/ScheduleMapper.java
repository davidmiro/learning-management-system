package com.david.learning_management_system.mapper;

import com.david.learning_management_system.dto.request.ScheduleCreateDto;
import com.david.learning_management_system.dto.request.ScheduleUpdateDto;
import com.david.learning_management_system.dto.response.ScheduleResponseDto;
import com.david.learning_management_system.model.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface ScheduleMapper {

    Schedule toEntity(ScheduleCreateDto scheduleCreateDto);
    ScheduleResponseDto toResponse(Schedule schedule);
    void updateFromDto(@MappingTarget Schedule schedule, ScheduleUpdateDto scheduleUpdateDto);
}

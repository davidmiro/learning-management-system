package com.david.learning_management_system.mapper;

import com.david.learning_management_system.dto.request.ScheduleCreateDto;
import com.david.learning_management_system.dto.request.ScheduleUpdateDto;
import com.david.learning_management_system.dto.response.ScheduleResponseDto;
import com.david.learning_management_system.model.Schedule;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.ERROR)

public interface ScheduleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "group", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "course", ignore = true)
    @BeanMapping(ignoreUnmappedSourceProperties = {"groupId", "courseId"})
    Schedule toEntity(ScheduleCreateDto scheduleCreateDto);

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "group.groupName", target = "groupName")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.courseName", target = "courseName")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.firstName", target = "teacherFirstName")
    @Mapping(source = "teacher.lastName", target = "teacherLastName")
    ScheduleResponseDto toResponse(Schedule schedule);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "group", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "course", ignore = true)
    void updateFromDto(@MappingTarget Schedule schedule, ScheduleUpdateDto scheduleUpdateDto);
}
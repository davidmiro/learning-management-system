package com.david.learning_management_system.mapper;

import com.david.learning_management_system.dto.request.TeacherCreateDto;
import com.david.learning_management_system.dto.request.TeacherUpdateDto;
import com.david.learning_management_system.dto.response.TeacherResponseDto;
import com.david.learning_management_system.model.Teacher;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface TeacherMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    Teacher toEntity(TeacherCreateDto teacherCreateDto);


    @BeanMapping(ignoreUnmappedSourceProperties = {"courses", "schedules"})
    TeacherResponseDto toResponse(Teacher teacher);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    void updateFromDto(@MappingTarget Teacher teacher, TeacherUpdateDto teacherUpdateDto);
}
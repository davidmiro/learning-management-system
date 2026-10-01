package com.david.learning_management_system.mapper;

import com.david.learning_management_system.dto.request.CourseCreateDto;
import com.david.learning_management_system.dto.request.CourseUpdateDto;
import com.david.learning_management_system.dto.response.CourseResponseDto;
import com.david.learning_management_system.model.Course;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        uses = TeacherMapper.class)
public interface CourseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "courseGroups", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @BeanMapping(ignoreUnmappedSourceProperties = "teacherId")
    Course toEntity(CourseCreateDto courseCreateDto);

    @BeanMapping(ignoreUnmappedSourceProperties = {"schedules", "deleted", "courseGroups"})
    CourseResponseDto toResponse(Course course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "courseGroups", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @BeanMapping(ignoreUnmappedSourceProperties = "teacherId")
    void updateFromDto(CourseUpdateDto courseUpdateDto, @MappingTarget Course course);
}
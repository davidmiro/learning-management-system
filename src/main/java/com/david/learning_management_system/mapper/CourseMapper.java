package com.david.learning_management_system.mapper;

import com.david.learning_management_system.dto.request.CourseCreateDto;
import com.david.learning_management_system.dto.request.CourseUpdateDto;
import com.david.learning_management_system.dto.response.CourseResponseDto;
import com.david.learning_management_system.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseCreateDto courseCreateDto);
    CourseResponseDto toResponse(Course course);
    void updateFromDto(@MappingTarget Course course, CourseUpdateDto courseUpdateDto);
}

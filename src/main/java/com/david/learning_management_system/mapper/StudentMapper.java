package com.david.learning_management_system.mapper;

import com.david.learning_management_system.dto.request.StudentCreateDto;
import com.david.learning_management_system.dto.request.StudentUpdateDto;
import com.david.learning_management_system.dto.response.StudentResponseDto;
import com.david.learning_management_system.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentCreateDto studentCreateDto);

    StudentResponseDto toResponse(Student student);

    void updateFromDto(@MappingTarget Student student, StudentUpdateDto studentUpdateDto);

}
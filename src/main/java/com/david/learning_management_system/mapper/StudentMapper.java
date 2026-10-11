package com.david.learning_management_system.mapper;

import com.david.learning_management_system.dto.request.StudentCreateDto;
import com.david.learning_management_system.dto.request.StudentUpdateDto;
import com.david.learning_management_system.dto.response.StudentResponseDto;
import com.david.learning_management_system.model.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "groups", ignore = true)
    @BeanMapping(ignoreUnmappedSourceProperties = "groupIds")
    Student toEntity(StudentCreateDto studentCreateDto);

    @Mapping(target = "groups", ignore = true)
    @BeanMapping(ignoreUnmappedSourceProperties = "groups")
    StudentResponseDto toResponse(Student student);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "groups", ignore = true)
    void updateFromDto(@MappingTarget Student student, StudentUpdateDto studentUpdateDto);
}
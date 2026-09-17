package com.david.learning_management_system.mapper;

import com.david.learning_management_system.dto.request.TeacherCreateDto;
import com.david.learning_management_system.dto.request.TeacherUpdateDto;
import com.david.learning_management_system.dto.response.TeacherResponseDto;
import com.david.learning_management_system.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    Teacher toEntity(TeacherCreateDto teacherCreateDto);
    TeacherResponseDto toResponse(Teacher teacher);
    void updateFromDto(@MappingTarget Teacher teacher, TeacherUpdateDto teacherUpdateDto);

}

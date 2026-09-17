package com.david.learning_management_system.service;

import com.david.learning_management_system.dto.request.TeacherCreateDto;
import com.david.learning_management_system.dto.request.TeacherUpdateDto;
import com.david.learning_management_system.dto.response.TeacherResponseDto;
import com.david.learning_management_system.mapper.TeacherMapper;
import com.david.learning_management_system.model.Teacher;
import com.david.learning_management_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherResponseDto getTeacherById(Long id) {
        return teacherMapper.toResponse(teacherRepository.findTeacherByIdOrThrow(id));
    }

    public TeacherResponseDto createTeacher(TeacherCreateDto teacherCreateDto) {

        Teacher newTeacher = teacherMapper.toEntity(teacherCreateDto);
        Teacher savedTeacher = teacherRepository.save(newTeacher);

        return teacherMapper.toResponse(savedTeacher);
    }

    public TeacherResponseDto updateTeacher(Long id, TeacherUpdateDto teacherUpdateDto) {

        Teacher existingTeacher = teacherRepository.findTeacherByIdOrThrow(id);
        teacherMapper.updateFromDto(existingTeacher, teacherUpdateDto);

        Teacher updateTeacher = teacherRepository.save(existingTeacher);

        return teacherMapper.toResponse(updateTeacher);
    }

    public void deleteTeacherById(Long id) {
        teacherRepository.deleteTeacherByIdOrThrow(id);
    }
}

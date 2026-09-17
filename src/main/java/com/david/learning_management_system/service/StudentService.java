package com.david.learning_management_system.service;

import com.david.learning_management_system.dto.request.StudentCreateDto;
import com.david.learning_management_system.dto.request.StudentUpdateDto;
import com.david.learning_management_system.dto.response.StudentResponseDto;
import com.david.learning_management_system.mapper.StudentMapper;
import com.david.learning_management_system.model.Student;
import com.david.learning_management_system.repository.StudentRepository;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentResponseDto getStudentById(Long id) {

        return studentMapper.toResponse(studentRepository.findStudentByIdOrThrow(id));
    }

    public StudentResponseDto createStudent(@NotNull StudentCreateDto studentCreateDto) {

        Student newStudent = studentMapper.toEntity(studentCreateDto);
        Student savedStudent = studentRepository.save(newStudent);

        return studentMapper.toResponse(savedStudent);
    }

    public StudentResponseDto updateStudent(Long id, StudentUpdateDto studentUpdateDto) {

        Student existingStudent = studentRepository.findStudentByIdOrThrow(id);
        studentMapper.updateFromDto(existingStudent, studentUpdateDto);

        Student updateStudent = studentRepository.save(existingStudent);

        return studentMapper.toResponse(updateStudent);
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteStudentByIdOrThrow(id);
    }

}

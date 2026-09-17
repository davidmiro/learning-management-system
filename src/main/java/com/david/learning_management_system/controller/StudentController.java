package com.david.learning_management_system.controller;

import com.david.learning_management_system.dto.request.StudentCreateDto;
import com.david.learning_management_system.dto.request.StudentUpdateDto;
import com.david.learning_management_system.dto.response.StudentResponseDto;
import com.david.learning_management_system.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor

public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }


    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(createDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id,
                                                            @Valid @RequestBody StudentUpdateDto updateDto) {
        return ResponseEntity.ok(studentService.updateStudent(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.david.learning_management_system.controller;


import com.david.learning_management_system.dto.request.TeacherCreateDto;
import com.david.learning_management_system.dto.request.TeacherUpdateDto;
import com.david.learning_management_system.dto.response.TeacherResponseDto;
import com.david.learning_management_system.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> getTeacherById(@PathVariable Long id) {

        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDto> createTeacher(@Valid @RequestBody TeacherCreateDto createDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(createDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> updateTeacher(@PathVariable Long id,
                                                            @Valid @RequestBody TeacherUpdateDto updateDto) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {

        teacherService.deleteTeacherById(id);
        return ResponseEntity.noContent().build();
    }

}

package com.david.learning_management_system.controller;

import com.david.learning_management_system.dto.request.CourseCreateDto;
import com.david.learning_management_system.dto.request.CourseUpdateDto;
import com.david.learning_management_system.dto.response.CourseResponseDto;
import com.david.learning_management_system.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(@Valid @RequestBody CourseCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(createDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseUpdateDto updateDto) {
        return ResponseEntity.ok(courseService.updateCourse(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{groupId}/courses/{courseId}")
    public ResponseEntity<CourseResponseDto> addGroupToCourse(
            @PathVariable Long courseId,
            @PathVariable Long groupId) {

        return ResponseEntity.status(HttpStatus.OK).body(courseService.addGroupToCourse(courseId, groupId));
    }

    @DeleteMapping("/{groupId}/courses/{courseId}")
    public ResponseEntity<Void> deleteGroupFromCourse(
            @PathVariable Long courseId,
            @PathVariable Long groupId) {

        courseService.deleteGroupFromCourse(courseId, groupId);
        return ResponseEntity.noContent().build();
    }
}
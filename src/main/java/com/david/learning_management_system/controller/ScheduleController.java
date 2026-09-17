package com.david.learning_management_system.controller;


import com.david.learning_management_system.dto.request.ScheduleCreateDto;
import com.david.learning_management_system.dto.request.ScheduleUpdateDto;

import com.david.learning_management_system.dto.response.ScheduleResponseDto;
import com.david.learning_management_system.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createScheduleForGroup(@Valid @RequestBody ScheduleCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createScheduleForGroup(createDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleUpdateDto updateDto) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable Long id) {
        scheduleService.deleteScheduleById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-group/{groupId}")
    public ResponseEntity<Page<ScheduleResponseDto>> getScheduleForGroup(
            @PathVariable Long groupId,
            Pageable pageable) {

        return ResponseEntity.ok(scheduleService.getScheduleForGroup(groupId, pageable));
    }

    @GetMapping("/by-teacher/{teacherId}")
    public ResponseEntity<Page<ScheduleResponseDto>> getScheduleForTeacher(
            @PathVariable Long teacherId,
            Pageable pageable) {

        return ResponseEntity.ok(scheduleService.getScheduleForTeacher(teacherId, pageable));
    }
}

package com.david.learning_management_system.controller;

import com.david.learning_management_system.dto.request.GroupCreateDto;
import com.david.learning_management_system.dto.request.GroupUpdateDto;
import com.david.learning_management_system.dto.response.GroupResponseDto;
import com.david.learning_management_system.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor

public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDto> getGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }


    @PostMapping
    public ResponseEntity<GroupResponseDto> createGroup(@Valid @RequestBody GroupCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.createGroup(createDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupResponseDto> updateGroup(@PathVariable Long id, @Valid @RequestBody GroupUpdateDto updateDto) {
        return ResponseEntity.ok(groupService.updateGroup(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        groupService.deleteGroupById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{groupId}/students/{studentId}")
    public ResponseEntity<GroupResponseDto> addStudentToGroup(
            @PathVariable Long studentId,
            @PathVariable Long groupId) {
        return ResponseEntity.status(HttpStatus.OK).body(groupService.addStudentToGroup(studentId, groupId));
    }

    @DeleteMapping("/{groupId}/students/{studentId}")
    public ResponseEntity<Void> deleteStudentFromGroup(
            @PathVariable Long studentId,
            @PathVariable Long groupId) {
        groupService.deleteStudentFromGroup(studentId, groupId);
        return ResponseEntity.noContent().build();
    }

}

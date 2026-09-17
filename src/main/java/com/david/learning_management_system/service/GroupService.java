package com.david.learning_management_system.service;

import com.david.learning_management_system.dto.request.GroupCreateDto;
import com.david.learning_management_system.dto.request.GroupUpdateDto;
import com.david.learning_management_system.dto.response.GroupResponseDto;
import com.david.learning_management_system.mapper.GroupMapper;
import com.david.learning_management_system.model.Group;
import com.david.learning_management_system.model.Student;
import com.david.learning_management_system.repository.GroupRepository;
import com.david.learning_management_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final GroupMapper groupMapper;

    public GroupResponseDto getGroupById(Long id) {

        return groupMapper.toResponse(groupRepository.findGroupByIdOrThrow(id));
    }

    public GroupResponseDto createGroup(GroupCreateDto groupCreateDto) {

        Group newGroup = groupMapper.toEntity(groupCreateDto);
        Group savedGroup = groupRepository.save(newGroup);

        return groupMapper.toResponse(savedGroup);
    }

    public GroupResponseDto updateGroup(Long id, GroupUpdateDto groupUpdateDto) {

        Group existingGroup = groupRepository.findGroupByIdOrThrow(id);
        groupMapper.updateFromDto(existingGroup, groupUpdateDto);

        Group updateGroup = groupRepository.save(existingGroup);

        return groupMapper.toResponse(updateGroup);
    }

    public void deleteGroupById(Long id) {
        groupRepository.deleteGroupByIdOrThrow(id);
    }

    public GroupResponseDto addStudentToGroup(Long studentId, Long groupId) {

        Student student = studentRepository.findStudentByIdOrThrow(studentId);
        Group group = groupRepository.findGroupByIdOrThrow(groupId);

        if (student.getGroups().contains(group)) {
            throw new IllegalArgumentException("Student already in this group");
        }
        student.getGroups().add(group);
        studentRepository.save(student);

        return groupMapper.toResponse(group);
    }

    public void deleteStudentFromGroup(Long studentId, Long groupId) {

        Student student = studentRepository.findStudentByIdOrThrow(studentId);
        Group group = groupRepository.findGroupByIdOrThrow(groupId);

        if (!student.getGroups().contains(group)) {
            throw new IllegalArgumentException("Student is not a member of this group");
        }

        if (student.getGroups().size() == 1) {
            throw new IllegalArgumentException("Student must belong to at least one group");
        }

        student.getGroups().remove(group);
        studentRepository.save(student);
    }
}
package com.david.learning_management_system.service;

import com.david.learning_management_system.dto.request.CourseCreateDto;
import com.david.learning_management_system.dto.request.CourseUpdateDto;
import com.david.learning_management_system.dto.response.CourseResponseDto;
import com.david.learning_management_system.mapper.CourseMapper;
import com.david.learning_management_system.model.Course;
import com.david.learning_management_system.model.Group;
import com.david.learning_management_system.model.Teacher;
import com.david.learning_management_system.repository.CourseRepository;
import com.david.learning_management_system.repository.GroupRepository;
import com.david.learning_management_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class CourseService {

    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final CourseMapper courseMapper;

    public CourseResponseDto getCourseById(Long id) {
        return courseMapper.toResponse(courseRepository.findCourseByIdOrThrow(id));
    }

    @Transactional
    public CourseResponseDto createCourse(CourseCreateDto dto) {

        Teacher teacher = teacherRepository.findTeacherByIdOrThrow(dto.teacherId());
        Course newCourse = courseMapper.toEntity(dto);
        newCourse.setTeacher(teacher);

        Course savedCourse = courseRepository.save(newCourse);

        return courseMapper.toResponse(savedCourse);
    }

    @Transactional
    public CourseResponseDto updateCourse(Long id, CourseUpdateDto dto) {

        Course existingCourse = courseRepository.findCourseByIdOrThrow(id);
        courseMapper.updateFromDto(existingCourse, dto);

        if (dto.teacherId() != null) {
            Teacher teacher = teacherRepository.findTeacherByIdOrThrow(dto.teacherId());
            existingCourse.setTeacher(teacher);
        }

        Course updateCourse = courseRepository.save(existingCourse);

        return courseMapper.toResponse(updateCourse);
    }

    public void deleteCourseById(Long id) {

        courseRepository.deleteCourseByIdOrThrow(id);
    }

    @Transactional
    public CourseResponseDto addGroupToCourse(Long courseId, Long groupId) {

        Group group = groupRepository.findGroupByIdOrThrow(groupId);
        Course course = courseRepository.findCourseByIdOrThrow(courseId);

        if (course.getGroups().contains(group)) {
            throw new IllegalArgumentException("Group already in this course");
        }
        course.getGroups().add(group);
        courseRepository.save(course);

        return courseMapper.toResponse(course);
    }

    @Transactional
    public void deleteGroupFromCourse(Long courseId, Long groupId) {

        Group group = groupRepository.findGroupByIdOrThrow(groupId);
        Course course = courseRepository.findCourseByIdOrThrow(courseId);

        course.getGroups().remove(group);
        courseRepository.save(course);
    }
}
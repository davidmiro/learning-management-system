package com.david.learning_management_system.service;

import com.david.learning_management_system.dto.request.ScheduleCreateDto;
import com.david.learning_management_system.dto.request.ScheduleUpdateDto;
import com.david.learning_management_system.dto.response.ScheduleResponseDto;
import com.david.learning_management_system.mapper.ScheduleMapper;
import com.david.learning_management_system.model.Course;
import com.david.learning_management_system.model.Group;
import com.david.learning_management_system.model.Schedule;
import com.david.learning_management_system.model.Teacher;
import com.david.learning_management_system.repository.*;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.ObjectUtils.allNotNull;


@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final CourseGroupRepository courseGroupRepository;

    public ScheduleResponseDto getScheduleById(Long id) {

        return scheduleMapper.toResponse(scheduleRepository.findScheduleByIdOrThrow(id));
    }

    @Transactional
    public ScheduleResponseDto createScheduleForGroup(ScheduleCreateDto scheduleCreateDto) {

        Group group = groupRepository.findGroupByIdOrThrow(scheduleCreateDto.groupId());
        Course course = courseRepository.findCourseByIdOrThrow(scheduleCreateDto.courseId());

        Teacher teacher = ofNullable(course.getTeacher())
                .orElseThrow(() -> new IllegalArgumentException("Course has no teacher, cannot create schedule"));

        Schedule schedule = scheduleMapper.toEntity(scheduleCreateDto);

        if (!courseGroupRepository.existsByCourseIdAndGroupId(
                scheduleCreateDto.courseId(),
                scheduleCreateDto.groupId())) {
            throw new IllegalArgumentException("Group is not assigned to this course, cannot create schedule");
        }

        schedule.setGroup(group);
        schedule.setCourse(course);
        schedule.setTeacher(teacher);

        return scheduleMapper.toResponse(scheduleRepository.save(schedule));
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateDto dto) {

        Schedule existingSchedule = scheduleRepository.findScheduleByIdOrThrow(id);

        if (allNotNull(dto.startDateTime(), dto.endDateTime())
                && dto.startDateTime().isAfter(dto.endDateTime())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        scheduleMapper.updateFromDto(existingSchedule, dto);
        Schedule updateSchedule = scheduleRepository.save(existingSchedule);

        return scheduleMapper.toResponse(updateSchedule);
    }

    @Transactional(readOnly = true)
    public Page<ScheduleResponseDto> getScheduleForGroup(Long groupId, Pageable pageable) {

        groupRepository.findGroupByIdOrThrow(groupId);

        return scheduleRepository.findByGroup_Id(groupId, pageable)
                .map(scheduleMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<ScheduleResponseDto> getScheduleForTeacher(Long teacherId, Pageable pageable) {
        teacherRepository.findTeacherByIdOrThrow(teacherId);

        return scheduleRepository.findByTeacher_Id(teacherId, pageable)
                .map(scheduleMapper::toResponse);
    }

    public void deleteScheduleById(Long id) {
        scheduleRepository.deleteScheduleByIdOrThrow(id);
    }

}
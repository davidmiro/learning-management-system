package com.david.learning_management_system.repository;

import com.david.learning_management_system.Exception.ScheduleNotFoundException;
import com.david.learning_management_system.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    default Schedule findScheduleByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found"));
    }

    default void deleteScheduleByIdOrThrow(Long id){
        Schedule schedule = findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found with id: "+ id));
        delete(schedule);
    }

    Page<Schedule> findByGroup_Id(Long groupId, Pageable pageable);
    Page<Schedule> findByTeacher_Id(Long teacherId, Pageable pageable);

    void deleteByEndDateTimeBefore(LocalDateTime date);
}

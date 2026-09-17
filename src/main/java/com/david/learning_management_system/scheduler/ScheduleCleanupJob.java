package com.david.learning_management_system.scheduler;

import com.david.learning_management_system.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ScheduleCleanupJob {

    private final ScheduleRepository scheduleRepository;

    @Scheduled(cron = "0 0 3 * * *") // каждый день в 3:00
    @Transactional
    public void deleteOldSchedules() {
        scheduleRepository.deleteByEndDateTimeBefore(
                LocalDateTime.now().minusYears(1)
        );
    }
}

package com.raid.tasks.domain.dto;

import com.raid.tasks.domain.entities.TaskPriority;
import com.raid.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}

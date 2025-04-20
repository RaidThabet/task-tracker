package com.raid.tasks.mappers.impl;

import com.raid.tasks.domain.dto.TaskDTO;
import com.raid.tasks.domain.entities.Task;
import com.raid.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDto(TaskDTO taskDTO) {
        return new Task(
                taskDTO.id(),
                taskDTO.title(),
                taskDTO.description(),
                taskDTO.dueDate(),
                taskDTO.status(),
                taskDTO.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDTO toDto(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }

}

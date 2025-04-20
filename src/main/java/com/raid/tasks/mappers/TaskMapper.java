package com.raid.tasks.mappers;

import com.raid.tasks.domain.dto.TaskDTO;
import com.raid.tasks.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDTO taskDTO);

    TaskDTO toDto(Task task);

}

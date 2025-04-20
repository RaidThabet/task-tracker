package com.raid.tasks.mappers;

import com.raid.tasks.domain.dto.TaskListDTO;
import com.raid.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDTO taskDTO);

    TaskListDTO toDto(TaskList task);

}

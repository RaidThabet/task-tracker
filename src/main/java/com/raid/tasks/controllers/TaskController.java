package com.raid.tasks.controllers;

import com.raid.tasks.domain.dto.TaskDTO;
import com.raid.tasks.mappers.TaskMapper;
import com.raid.tasks.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists/{task-list-id}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping("")
    public List<TaskDTO> listTasks(
            @PathVariable("task-list-id") UUID taskListId
    ) {
        return taskService.listTasks(taskListId).stream()
                .map(taskMapper::toDto)
                .toList();
    }
}

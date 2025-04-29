package com.raid.tasks.controllers;

import com.raid.tasks.domain.dto.TaskDTO;
import com.raid.tasks.domain.entities.Task;
import com.raid.tasks.mappers.TaskMapper;
import com.raid.tasks.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @PostMapping("")
    public TaskDTO createTask(
            @PathVariable("task-list-id") UUID taskListId,
            @RequestBody TaskDTO taskDTO
    ) {
        Task createdTask = taskService.createTask(taskListId, taskMapper.fromDto(taskDTO));

        return taskMapper.toDto(createdTask);
    }

    @GetMapping("{task-id}")
    public Optional<TaskDTO> getTask(
            @PathVariable("task-list-id") UUID taskListId,
            @PathVariable("task-id") UUID taskId
    ) {
        return taskService.getTask(taskListId, taskId).map(taskMapper::toDto);
    }
}

package com.raid.tasks.controllers;

import com.raid.tasks.domain.dto.TaskListDTO;
import com.raid.tasks.domain.entities.TaskList;
import com.raid.tasks.mappers.TaskListMapper;
import com.raid.tasks.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    @GetMapping
    public List<TaskListDTO> listTaskLists() {
        var taskLists = taskListService.listTaskLists();

        return taskLists.stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDTO createTaskList(
            @RequestBody TaskListDTO taskListDTO
    ) {
        var createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDTO)
        );

        return taskListMapper.toDto(createdTaskList);
    }

    @GetMapping("{task-list-id}")
    public Optional<TaskListDTO> getTaskList(
            @PathVariable("task-list-id") UUID id
    ) {
        return taskListService.getTaskList(id).map(taskListMapper::toDto);
    }

    @PutMapping("{task-list-id}")
    public TaskListDTO updateTaskList(
            @PathVariable("task-list-id") UUID taskListId,
            @RequestBody TaskListDTO taskListDTO
    ) {
        TaskList updtedTaskList = taskListService.updateTaskList(
                taskListId,
                taskListMapper.fromDto(taskListDTO)
        );

        return taskListMapper.toDto(updtedTaskList);
    }

    @DeleteMapping("{task-list-id}")
    public void deleteTaskList(
            @PathVariable("task-list-id") UUID id
    ) {
        taskListService.deleteTaskList(id);
    }

}

package com.raid.tasks.controllers;

import com.raid.tasks.domain.dto.TaskListDTO;
import com.raid.tasks.mappers.TaskListMapper;
import com.raid.tasks.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}

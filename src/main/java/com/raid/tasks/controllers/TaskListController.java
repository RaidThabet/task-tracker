package com.raid.tasks.controllers;

import com.raid.tasks.domain.dto.TaskListDTO;
import com.raid.tasks.domain.entities.TaskList;
import com.raid.tasks.domain.entities.User;
import com.raid.tasks.domain.entities.UserRole;
import com.raid.tasks.mappers.TaskListMapper;
import com.raid.tasks.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<?> listTaskLists() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        var userRole = user.getRole();
        var userId = userRole == UserRole.ROLE_USER ? user.getId() : null;
        var taskLists = taskListService.listTaskLists(userId);

        var list = taskLists.stream()
                .map(taskListMapper::toDto)
                .toList();

        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') and !hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public TaskListDTO createTaskList(
            @RequestBody TaskListDTO taskListDTO
    ) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        var createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDTO),
                user
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

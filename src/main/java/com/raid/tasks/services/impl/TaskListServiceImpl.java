package com.raid.tasks.services.impl;

import com.raid.tasks.domain.entities.TaskList;
import com.raid.tasks.repositories.TaskListRepository;
import com.raid.tasks.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (taskList.getId() != null) {
            throw new IllegalArgumentException("Task list already has an ID");
        }
        if (taskList.getTitle() == null || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task list title must be present");
        }

        var newTaskList = new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                null,
                null
        );

        return taskListRepository.save(newTaskList);
    }
}

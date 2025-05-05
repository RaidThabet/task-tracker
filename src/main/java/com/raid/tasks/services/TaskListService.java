package com.raid.tasks.services;

import com.raid.tasks.domain.entities.TaskList;
import com.raid.tasks.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> listTaskLists(UUID userId);
    TaskList createTaskList(TaskList taskList, User user);
    Optional<TaskList> getTaskList(UUID id);
    TaskList updateTaskList(UUID taskListId, TaskList taskList);
    void deleteTaskList(UUID taskListId);
}

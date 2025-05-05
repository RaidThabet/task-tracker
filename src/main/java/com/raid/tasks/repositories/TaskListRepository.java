package com.raid.tasks.repositories;

import com.raid.tasks.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskListRepository extends JpaRepository<TaskList, UUID> {
    List<TaskList> findByUserId(UUID userId);
}

package com.nick.task_management_api.repository;

import com.nick.task_management_api.entity.Task;
import com.nick.task_management_api.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    // Find all tasks for a specific user
    List<Task> findByUserId(UUID userId);

    // Find tasks by user and status
    List<Task> findByUserIdAndStatus(UUID userId, TaskStatus status);
}

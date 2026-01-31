package com.nick.task_management_api.controller;

import com.nick.task_management_api.dto.CreateTaskRequest;
import com.nick.task_management_api.dto.TaskResponse;
import com.nick.task_management_api.dto.UpdateTaskRequest;
import com.nick.task_management_api.entity.TaskStatus;
import com.nick.task_management_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @RequestParam UUID userId,
            @Valid @RequestBody CreateTaskRequest request) {

        TaskResponse response = taskService.createTask(userId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks(
            @RequestParam UUID userId,
            @RequestParam(required = false) TaskStatus status) {

        List<TaskResponse> tasks;

        if (status != null) {
            tasks = taskService.getTasksByUserAndStatus(userId, status);
        } else {
            tasks = taskService.getTasksByUser(userId);
        }

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable UUID taskId) {
        TaskResponse response = taskService.getTaskById(taskId);
        return ResponseEntity.ok(response);
    }

    // Update a task
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable UUID taskId,
            @Valid @RequestBody UpdateTaskRequest request) {

        TaskResponse response = taskService.updateTask(taskId, request);
        return ResponseEntity.ok(response);
    }

    // Delete a task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    // Toggle task status (PENDING <-> COMPLETED)
    @PatchMapping("/{taskId}/status")
    public ResponseEntity<TaskResponse> toggleTaskStatus(@PathVariable UUID taskId) {
        TaskResponse response = taskService.toggleTaskStatus(taskId);
        return ResponseEntity.ok(response);
    }
}
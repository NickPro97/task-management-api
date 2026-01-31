package com.nick.task_management_api.service;

import com.nick.task_management_api.dto.CreateTaskRequest;
import com.nick.task_management_api.dto.TaskResponse;
import com.nick.task_management_api.dto.UpdateTaskRequest;
import com.nick.task_management_api.entity.Task;
import com.nick.task_management_api.entity.TaskStatus;
import com.nick.task_management_api.entity.User;
import com.nick.task_management_api.exception.ResourceNotFoundException;
import com.nick.task_management_api.repository.TaskRepository;
import com.nick.task_management_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskResponse createTask(UUID userId, CreateTaskRequest request) {
        // 1. Find the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setStatus(TaskStatus.PENDING);
        task.setUser(user);

        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    public List<TaskResponse> getTasksByUser(UUID userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        List<TaskResponse> responses = new ArrayList<>();
        for (Task task : tasks) {
            responses.add(mapToResponse(task));
        }
        return responses;
    }

    public List<TaskResponse> getTasksByUserAndStatus(UUID userId, TaskStatus status) {
        List<Task> tasks = taskRepository.findByUserIdAndStatus(userId, status);
        List<TaskResponse> responses = new ArrayList<>();
        for (Task task : tasks) {
            responses.add(mapToResponse(task));
        }
        return responses;
    }

    public TaskResponse getTaskById(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        return mapToResponse(task);
    }

    private TaskResponse mapToResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setDueDate(task.getDueDate());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        return response;
    }

    // Update a task
    public TaskResponse updateTask(UUID taskId, UpdateTaskRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }
        if (request.getDueDate() != null) {
            task.setDueDate(request.getDueDate());
        }
        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }

        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }

    // Delete a task
    public void deleteTask(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskRepository.delete(task);
    }

    // Toggle task status (PENDING <-> COMPLETED)
    public TaskResponse toggleTaskStatus(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if (task.getStatus() == TaskStatus.PENDING) {
            task.setStatus(TaskStatus.COMPLETED);
        } else {
            task.setStatus(TaskStatus.PENDING);
        }

        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }
}

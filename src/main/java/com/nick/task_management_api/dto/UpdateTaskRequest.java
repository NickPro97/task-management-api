package com.nick.task_management_api.dto;

import com.nick.task_management_api.entity.TaskStatus;
import java.time.LocalDate;

public class UpdateTaskRequest {

    private String title;

    private String description;

    private LocalDate dueDate;

    private TaskStatus status;

    public UpdateTaskRequest() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
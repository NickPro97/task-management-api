package com.nick.task_management_api.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class CreateTaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private LocalDate dueDate;

    // Default constructor
    public CreateTaskRequest() {
    }

    // Constructor with all fields
    public CreateTaskRequest(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}

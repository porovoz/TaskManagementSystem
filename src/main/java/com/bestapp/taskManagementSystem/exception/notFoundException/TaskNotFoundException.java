package com.bestapp.taskManagementSystem.exception.notFoundException;

public class TaskNotFoundException extends NotFoundException {
    public TaskNotFoundException(String message) {
        super("Task not found");
    }
}

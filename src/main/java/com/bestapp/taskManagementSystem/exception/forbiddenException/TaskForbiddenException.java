package com.bestapp.taskManagementSystem.exception.forbiddenException;

public class TaskForbiddenException extends ForbiddenException {
    public TaskForbiddenException(String message) {
        super("Not enough rights to edit the task");
    }
}

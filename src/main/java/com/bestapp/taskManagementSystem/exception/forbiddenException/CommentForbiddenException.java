package com.bestapp.taskManagementSystem.exception.forbiddenException;

public class CommentForbiddenException extends ForbiddenException {
    public CommentForbiddenException(String message) {
        super("Not enough rights to edit the comment");
    }
}

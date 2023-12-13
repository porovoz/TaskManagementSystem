package com.bestapp.taskManagementSystem.exception.notFoundException;

public class CommentNotFoundException extends NotFoundException {
    public CommentNotFoundException(String message) {
        super("Comment not found");
    }
}

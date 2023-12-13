package com.bestapp.taskManagementSystem.exception.invalidRegistrationParameterException;

public class InvalidUsernameException extends InvalidRegistrationParameterException {
    public InvalidUsernameException(String message) {
        super("The user with this email has already been registered");
    }
}

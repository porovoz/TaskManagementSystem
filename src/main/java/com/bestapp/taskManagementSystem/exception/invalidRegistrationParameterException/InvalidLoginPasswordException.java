package com.bestapp.taskManagementSystem.exception.invalidRegistrationParameterException;

public class InvalidLoginPasswordException extends InvalidRegistrationParameterException {
    public InvalidLoginPasswordException(String message) {
        super("Check if login and password are correct");
    }
}

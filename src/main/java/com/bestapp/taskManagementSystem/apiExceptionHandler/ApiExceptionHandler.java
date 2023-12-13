package com.bestapp.taskManagementSystem.apiExceptionHandler;

import com.bestapp.taskManagementSystem.exception.forbiddenException.ForbiddenException;
import com.bestapp.taskManagementSystem.exception.invalidRegistrationParameterException.InvalidRegistrationParameterException;
import com.bestapp.taskManagementSystem.exception.notFoundException.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * An auxiliary class for handling exceptions that occur when controllers are running.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidRegistrationParameterException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String invalidRegistrationParameterExceptionHandler(InvalidRegistrationParameterException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String forbiddenExceptionHandler(ForbiddenException e) {
        return e.getMessage();
    }
}

package com.natanight.petproject.errorhandlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> pageNotFound(
            DataIntegrityViolationException ex,
            HttpServletRequest request
    ) {
        return errorBuild(ex.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> validationException(
            DataIntegrityViolationException ex,
            HttpServletRequest request
    ) {
        return errorBuild(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(
            AccessDeniedException ex,
            HttpServletRequest request
    ) {
        String message = "You do not have permission to perform this action: " + ex;
        return errorBuild(message, request, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolation(
            DataIntegrityViolationException ex,
            HttpServletRequest request
    ) {
        String message = "Invalid data";
        if (ex.getCause() instanceof ConstraintViolationException cve) {
            if ("unique_username".equals(cve.getConstraintName())) {
                message = "Username already exists";
            }
        }
        return errorBuild(message, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {
        return errorBuild(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiError> errorBuild(
            String message,
            HttpServletRequest request,
            HttpStatus httpStatus
    ) {
        ApiError error = new ApiError(httpStatus, message, request.getRequestURI());
        return new ResponseEntity<>(error, httpStatus);
    }
}
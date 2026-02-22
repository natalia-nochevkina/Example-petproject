package com.natanight.petproject.errorhandlers;

import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseExceptionHandler {
    // 405
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> httpRequestMethodNotSupportedException(
            NoHandlerFoundException ex,
            HttpServletRequest request
    ) {
        return errorBuild(ex.getMessage(), request, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // 401 no auth
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> authenticationException(
            AccessDeniedException ex,
            HttpServletRequest request
    ) {
        String message = "You are not authorized: " + ex;
        return errorBuild(message, request, HttpStatus.UNAUTHORIZED);
    }

    // 403 forbidden
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(
            AccessDeniedException ex,
            HttpServletRequest request
    ) {
        String message = "You do not have permission to perform this action: " + ex;
        return errorBuild(message, request, HttpStatus.FORBIDDEN);
    }


    // 409 DB exceptions
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

    // 500 General error handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {
        return errorBuild(
                ex.getClass().getName() + ": \n" + ex.getMessage(),
                request,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
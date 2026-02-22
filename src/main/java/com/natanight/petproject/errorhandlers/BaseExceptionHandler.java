package com.natanight.petproject.errorhandlers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
abstract public class BaseExceptionHandler {
    protected ResponseEntity<ApiError> errorBuild(
            String message,
            HttpServletRequest request,
            HttpStatus httpStatus
    ) {
        ApiError error = new ApiError(httpStatus, message, request.getRequestURI());
        return new ResponseEntity<>(error, httpStatus);
    }
}

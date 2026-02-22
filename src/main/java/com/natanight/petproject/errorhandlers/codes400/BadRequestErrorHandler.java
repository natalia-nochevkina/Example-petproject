package com.natanight.petproject.errorhandlers.codes400;

import com.natanight.petproject.errorhandlers.ApiError;
import com.natanight.petproject.errorhandlers.BaseExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.exc.UnrecognizedPropertyException;

// TODO
//  Probably makes sense to make method "getStatus" for all error handlers
//  But that will "glue" each class to specific status

// 400 Handler
@RestControllerAdvice
@Order(1)
public class BadRequestErrorHandler extends BaseExceptionHandler {
    private final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    // Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> methodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        return errorBuild(ex.getMessage(), request, STATUS);
    }

    // JSON errors
    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<ApiError> unrecognizedPropertyException(
            UnrecognizedPropertyException ex,
            HttpServletRequest request
    ) {
        return errorBuild(ex.getMessage(), request, STATUS);
    }

    // JSON not readable
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> httpMessageNotReadableException(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        return errorBuild(ex.getMessage(), request, STATUS);
    }
}

package com.natanight.petproject.errorhandlers.codes400;

import com.natanight.petproject.errorhandlers.ApiError;
import com.natanight.petproject.errorhandlers.BaseExceptionHandler;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

// 404 Handler
@RestControllerAdvice
@Order(1)
public class NotFoundExceptionHandler extends BaseExceptionHandler {
    private final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    // 404
    // TODO: check if this thing even being used
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> entityNotFoundException(
            EntityNotFoundException ex,
            HttpServletRequest request
    ) {
        return errorBuild(ex.getMessage(), request, STATUS);
    }

    // 404
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> noHandlerFoundException(
            NoHandlerFoundException ex,
            HttpServletRequest request
    ) {
        return errorBuild(ex.getMessage(), request, STATUS);
    }
}

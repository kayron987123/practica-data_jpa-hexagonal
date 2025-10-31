package com.gad.spring_data_jpa.common.exception;

import com.gad.spring_data_jpa.common.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.gad.spring_data_jpa.common.utils.MethodUtils.buildErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex,
            HttpServletRequest request) {

        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> {
                    String[] pathParts = violation.getPropertyPath().toString().split("\\.");
                    return pathParts[pathParts.length - 1] + ": " + violation.getMessage();
                })
                .sorted()
                .toList();

        return buildErrorResponse(request, HttpStatus.BAD_REQUEST, "Validation incorrect", errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .sorted()
                .toList();

        return buildErrorResponse(request, HttpStatus.BAD_REQUEST, "Validation incorrect", errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        return buildErrorResponse(request, HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    }
}

package com.gad.spring_data_jpa.department.infrastructure.adapters.input.rest;

import com.gad.spring_data_jpa.common.dto.response.ErrorResponse;
import com.gad.spring_data_jpa.department.domain.exception.DepartmentNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.gad.spring_data_jpa.common.utils.MethodUtils.buildErrorResponse;

@RestControllerAdvice
public class DepartmentExceptionHandler {
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentNotFoundException(DepartmentNotFoundException ex,
                                                                           HttpServletRequest request) {

        return buildErrorResponse(request, HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }
}

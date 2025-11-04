package com.gad.spring_data_jpa.teacher.infrastructure.adapters.input.rest;

import com.gad.spring_data_jpa.common.dto.response.ErrorResponse;
import com.gad.spring_data_jpa.teacher.domain.exception.TeacherNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.gad.spring_data_jpa.common.utils.MethodUtils.buildErrorResponse;

@RestControllerAdvice
public class TeacherExceptionHandler {
    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTeacherNotFoundException(TeacherNotFoundException ex,
                                                                        HttpServletRequest request) {
        return buildErrorResponse(request, HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }
}

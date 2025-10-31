package com.gad.spring_data_jpa.common.utils;

import com.gad.spring_data_jpa.common.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MethodUtils {
    private MethodUtils() {
    }

    public static String dateTimeNowFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public static ResponseEntity<ErrorResponse> buildErrorResponse(HttpServletRequest request,
                                                             HttpStatus status,
                                                             String message,
                                                             List<String> errors) {

        String fullPath = request.getRequestURI();
        String query = request.getQueryString();
        String pathWithParams = query != null ? fullPath + "?" + query : fullPath;

        String methodHttp = request.getMethod();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status.value())
                .message(message)
                .errors(errors)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .path(methodHttp + ": " + pathWithParams)
                .build();

        return ResponseEntity.status(status).body(errorResponse);
    }
}

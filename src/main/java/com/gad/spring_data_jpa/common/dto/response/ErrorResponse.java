package com.gad.spring_data_jpa.common.dto.response;

import lombok.Builder;

@Builder
public record ErrorResponse(
        int status,
        String message,
        Object errors,
        String timestamp,
        String path
) {
}

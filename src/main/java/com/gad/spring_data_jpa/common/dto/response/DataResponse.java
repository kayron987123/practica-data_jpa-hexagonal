package com.gad.spring_data_jpa.common.dto.response;

import lombok.Builder;

@Builder
public record DataResponse<T>(
        int status,
        String message,
        T data,
        String timestamp
) {
}

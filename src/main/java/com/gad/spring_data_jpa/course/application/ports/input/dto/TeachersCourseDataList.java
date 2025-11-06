package com.gad.spring_data_jpa.course.application.ports.input.dto;

import lombok.Builder;

@Builder
public record TeachersCourseDataList(
        Long teacherId,
        String teacherName
) {
}

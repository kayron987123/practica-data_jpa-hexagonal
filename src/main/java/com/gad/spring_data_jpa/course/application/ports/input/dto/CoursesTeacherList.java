package com.gad.spring_data_jpa.course.application.ports.input.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CoursesTeacherList(
        Long teacherId,
        String teacherName,
        List<CoursesTeacherDataList> coursesDataList
) {
}

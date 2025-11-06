package com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response;

import com.gad.spring_data_jpa.course.application.ports.input.dto.CoursesTeacherDataList;

import java.util.List;

public record CoursesTeacherListResponse(
        Long teacherId,
        String teacherName,
        List<CoursesTeacherDataList> coursesDataList
) {
}

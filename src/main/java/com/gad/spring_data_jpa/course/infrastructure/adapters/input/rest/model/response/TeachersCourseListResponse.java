package com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response;

import com.gad.spring_data_jpa.course.application.ports.input.dto.TeachersCourseDataList;

import java.util.List;

public record TeachersCourseListResponse(
        Long courseId,
        String courseName,
        Integer credits,
        String departmentName,
        List<TeachersCourseDataList> teachersCourseDataLists
) {
}

package com.gad.spring_data_jpa.course.application.ports.input.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TeachersCourseList(
        Long courseId,
        String courseName,
        Integer credits,
        String departmentName,
        List<TeachersCourseDataList> teachersCourseDataLists
) {
}

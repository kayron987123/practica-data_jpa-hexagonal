package com.gad.spring_data_jpa.course.domain.model;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Objects;

@Builder
public record CourseTeacherAssignment(
        Long courseId,
        Long teacherId,
        LocalDate assignedDate
) {
    public CourseTeacherAssignment {
        Objects.requireNonNull(assignedDate, "Assigned date cannot be null");

        if (assignedDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Assigned date cannot be in the past");
        }
    }
}

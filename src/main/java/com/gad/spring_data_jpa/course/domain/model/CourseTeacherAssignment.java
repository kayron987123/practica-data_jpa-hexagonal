package com.gad.spring_data_jpa.course.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public record CourseTeacherAssignment(
        String courseName,
        String teacherName,
        LocalDate assignedDate
) {
    public CourseTeacherAssignment {
        Objects.requireNonNull(courseName, "Course name cannot be null");
        Objects.requireNonNull(teacherName, "Teacher name cannot be null");
        Objects.requireNonNull(assignedDate, "Assigned date cannot be null");

        if (courseName.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be null or blank");
        }
        if (teacherName.isBlank()) {
            throw new IllegalArgumentException("Teacher name cannot be null or blank");
        }
        if (assignedDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Assigned date cannot be in the past");
        }
    }

    public static CourseTeacherAssignment create(String courseName, String teacherName, LocalDate assignedDate) {
        return new CourseTeacherAssignment(courseName.trim(), teacherName.trim(), assignedDate);
    }

    public CourseTeacherAssignment withCourseName(String courseName) {
        Objects.requireNonNull(courseName, "Course name cannot be null");
        return new CourseTeacherAssignment(courseName.trim(), this.teacherName, this.assignedDate);
    }

    public CourseTeacherAssignment withTeacherName(String teacherName) {
        Objects.requireNonNull(teacherName, "Teacher name cannot be null");
        return new CourseTeacherAssignment(this.courseName, teacherName.trim(), this.assignedDate);
    }

    public CourseTeacherAssignment withAssignedDate(LocalDate assignedDate) {
        Objects.requireNonNull(assignedDate, "Assigned date cannot be null");
        return new CourseTeacherAssignment(this.courseName, this.teacherName, assignedDate);
    }

    public boolean isAssignedToCourse(String courseName) {
        if (courseName == null || courseName.isBlank()) return false;
        return this.courseName.equalsIgnoreCase(courseName.trim());
    }
}

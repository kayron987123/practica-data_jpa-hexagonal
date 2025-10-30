package com.gad.spring_data_jpa.exam.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public record Exams(
        String courseName,
        String title,
        LocalDate examDate
) {
    public Exams {
        Objects.requireNonNull(courseName, "Course name cannot be null");
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(examDate, "Exam date cannot be null");

        if (courseName.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be null or blank");
        }

        if (title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }

        if (examDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Exam date cannot be in the past");
        }
    }

    public static Exams create(String courseName, String title, LocalDate examDate) {
        return new Exams(courseName.trim(), title.trim(), examDate);
    }

    public Exams withCourseName(String courseName) {
        Objects.requireNonNull(courseName, "Course name cannot be null");
        return new Exams(courseName.trim(), this.title, this.examDate);
    }

    public Exams withTitle(String title) {
        Objects.requireNonNull(title, "Title cannot be null");
        return new Exams(this.courseName, title.trim(), this.examDate);
    }

    public Exams withExamDate(LocalDate examDate) {
        Objects.requireNonNull(examDate, "Exam date cannot be null");
        return new Exams(this.courseName, this.title, examDate);
    }
}

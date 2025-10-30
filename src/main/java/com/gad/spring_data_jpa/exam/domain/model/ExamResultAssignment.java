package com.gad.spring_data_jpa.exam.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public record ExamResultAssignment(
        String examTitle,
        String studentName,
        BigDecimal score
) {
    public ExamResultAssignment {
        Objects.requireNonNull(examTitle, "Exam title cannot be null");
        Objects.requireNonNull(studentName, "Student name cannot be null");
        Objects.requireNonNull(score, "Score cannot be null");

        if (examTitle.isBlank()) {
            throw new IllegalArgumentException("Exam title cannot be blank");
        }

        if (studentName.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be blank");
        }

        if (score.compareTo(BigDecimal.ZERO) < 0 || score.compareTo(new BigDecimal("20")) > 0) {
            throw new IllegalArgumentException("Score must be between 0 and 20");
        }
    }

    public static ExamResultAssignment create(String examTitle, String studentName, BigDecimal score) {
        return new ExamResultAssignment(examTitle.trim(), studentName.trim(), score);
    }

    public ExamResultAssignment withExamTitle(String examTitle) {
        Objects.requireNonNull(examTitle, "Exam title cannot be null");
        return new ExamResultAssignment(examTitle.trim(), this.studentName, this.score);
    }

    public ExamResultAssignment withStudentName(String studentName) {
        Objects.requireNonNull(studentName, "Student name cannot be null");
        return new ExamResultAssignment(this.examTitle, studentName.trim(), this.score);
    }

    public ExamResultAssignment withScore(BigDecimal score) {
        Objects.requireNonNull(score, "Score cannot be null");
        return new ExamResultAssignment(this.examTitle, this.studentName, score);
    }
}

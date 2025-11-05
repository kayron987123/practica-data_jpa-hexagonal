package com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.entity;

import com.gad.spring_data_jpa.teacher.infrastructure.adapters.output.persistence.entity.TeacherEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "course_teachers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseTeacherEntity {

    @EmbeddedId
    private CourseTeacherId id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @Column(name = "assigned_date")
    private LocalDate assignedDate;
}

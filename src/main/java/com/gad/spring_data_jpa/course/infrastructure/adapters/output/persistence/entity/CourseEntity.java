package com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.entity;

import com.gad.spring_data_jpa.department.infrastructure.adapters.output.persistence.entity.DepartmentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer credits;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;
}

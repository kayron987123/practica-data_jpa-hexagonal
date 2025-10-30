package com.gad.spring_data_jpa.course.domain.model;

import java.util.Objects;

public record Course(
        Long id,
        String name,
        Integer credits,
        String departmentName
) {
    private static final int MIN_CREDITS = 1;
    private static final int MAX_CREDITS = 10;

    public Course {
        Objects.requireNonNull(id, "id is null");
        Objects.requireNonNull(name, "Course name cannot be null");
        Objects.requireNonNull(credits, "Course credits cannot be null");
        Objects.requireNonNull(departmentName, "Course department name cannot be null");

        if (id <= 0) {
            throw new IllegalArgumentException("Course id cannot be 0 or negative");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be blank");
        }

        if (departmentName.isBlank()) {
            throw new IllegalArgumentException("Course department name cannot be blank");
        }

        if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
            throw new IllegalArgumentException(
                    String.format("Course credits must be between %d and %d", MIN_CREDITS, MAX_CREDITS)
            );
        }
    }

    public static Course create(Long id,String name, Integer credits, String departmentName) {
        return new Course(id, name.trim(), credits, departmentName.trim());
    }

    public Course withName(String newName) {
        Objects.requireNonNull(newName, "Name cannot be null");
        return new Course(this.id, newName.trim(), this.credits, this.departmentName);
    }

    public Course withCredits(Integer newCredits) {
        Objects.requireNonNull(newCredits, "Credits cannot be null");
        return new Course(this.id, this.name, newCredits, this.departmentName);
    }

    public Course withDepartmentName(String newDepartmentName) {
        Objects.requireNonNull(newDepartmentName, "Department name cannot be null");
        return new Course(this.id, this.name, this.credits, newDepartmentName.trim());
    }

    public boolean belongsToDepartment(String departmentName) {
        if (departmentName == null || departmentName.isBlank()) return false;
        return this.departmentName.equalsIgnoreCase(departmentName.trim());
    }
}

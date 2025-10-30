package com.gad.spring_data_jpa.department.domain.model;

import java.util.Objects;

public record Department(
        String name,
        String faculty
) {
    public Department {
        Objects.requireNonNull(name, "Department name cannot be null");
        Objects.requireNonNull(faculty, "Faculty cannot be null");

        if (name.isBlank()) {
            throw new IllegalArgumentException("Department name cannot be blank");
        }

        if (faculty.isBlank()) {
            throw new IllegalArgumentException("Faculty cannot be blank");
        }
    }

    public static Department create(String name, String faculty) {
        return new Department(name.trim(), faculty.trim());
    }

    public Department withName(String newName) {
        return new Department(Objects.requireNonNull(newName, "Name cannot be null").trim(), this.faculty);
    }

    public Department withFaculty(String newFaculty) {
        return new Department(this.name, Objects.requireNonNull(newFaculty, "Faculty cannot be null").trim());
    }

    public boolean belongsToFaculty(String facultyName) {
        if (facultyName == null || facultyName.isBlank()) return false;
        return this.faculty.equalsIgnoreCase(facultyName.trim());
    }
}

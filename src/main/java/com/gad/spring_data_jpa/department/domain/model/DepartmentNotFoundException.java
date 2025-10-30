package com.gad.spring_data_jpa.department.domain.model;

public class DepartmentNotFoundException extends RuntimeException {
    public  DepartmentNotFoundException(String message) {
        super(message);
    }
}

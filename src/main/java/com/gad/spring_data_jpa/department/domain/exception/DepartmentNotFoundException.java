package com.gad.spring_data_jpa.department.domain.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public  DepartmentNotFoundException(String message) {
        super(message);
    }
}

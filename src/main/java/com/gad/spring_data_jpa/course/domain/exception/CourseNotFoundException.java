package com.gad.spring_data_jpa.course.domain.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(String message) {
        super(message);
    }


}

package com.gad.spring_data_jpa.department.application.application.ports.input;


import com.gad.spring_data_jpa.department.application.application.ports.input.dto.CreateDepartmentCommand;
import com.gad.spring_data_jpa.department.application.application.ports.input.dto.DepartmentDto;
import com.gad.spring_data_jpa.department.application.application.ports.input.dto.UpdateDepartmentCommand;

import java.util.List;

public interface DepartmentUseCase {
    DepartmentDto createCourse(CreateDepartmentCommand command);

    DepartmentDto getCourseById(Long departmentId);

    DepartmentDto updateCourse(Long departmentId, UpdateDepartmentCommand command);

    List<DepartmentDto> getAllCourses();

    void deleteCourseById(Long departmentId);
}

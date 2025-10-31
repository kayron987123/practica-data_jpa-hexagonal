package com.gad.spring_data_jpa.department.application.application.ports.input;


import com.gad.spring_data_jpa.department.application.application.ports.input.dto.CreateDepartmentCommand;
import com.gad.spring_data_jpa.department.application.application.ports.input.dto.DepartmentDto;
import com.gad.spring_data_jpa.department.application.application.ports.input.dto.UpdateDepartmentCommand;

import java.util.List;

public interface DepartmentUseCase {
    DepartmentDto createDepartment(CreateDepartmentCommand command);
    DepartmentDto getDepartmentById(Long departmentId);
    DepartmentDto updateDepartment(Long departmentId, UpdateDepartmentCommand command);
    List<DepartmentDto> getAllDepartments();
    void deleteDepartmentById(Long departmentId);
    DepartmentDto getDepartmentByName(String departmentName);
}

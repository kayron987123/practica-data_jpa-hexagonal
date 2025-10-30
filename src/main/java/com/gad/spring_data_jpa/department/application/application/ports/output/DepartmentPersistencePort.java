package com.gad.spring_data_jpa.department.application.application.ports.output;

import com.gad.spring_data_jpa.department.domain.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentPersistencePort {
    Department save(Department department);
    Optional<Department> findById(Long departmentId);
    List<Department> findAll();
    void deleteById(Long departmentId);
    Optional<Department> findByName(String name);
}

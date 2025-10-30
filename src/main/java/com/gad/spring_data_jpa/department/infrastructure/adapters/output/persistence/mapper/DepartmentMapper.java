package com.gad.spring_data_jpa.department.infrastructure.adapters.output.persistence.mapper;

import com.gad.spring_data_jpa.department.domain.model.Department;
import com.gad.spring_data_jpa.department.infrastructure.adapters.output.persistence.entity.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
    DepartmentEntity departmentToEntity(Department department);

    Department entityToDepartment(DepartmentEntity departmentEntity);
}

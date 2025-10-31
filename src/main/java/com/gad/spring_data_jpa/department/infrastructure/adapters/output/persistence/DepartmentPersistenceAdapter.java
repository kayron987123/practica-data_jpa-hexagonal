package com.gad.spring_data_jpa.department.infrastructure.adapters.output.persistence;

import com.gad.spring_data_jpa.department.application.application.ports.output.DepartmentPersistencePort;
import com.gad.spring_data_jpa.department.domain.model.Department;
import com.gad.spring_data_jpa.department.domain.exception.DepartmentNotFoundException;
import com.gad.spring_data_jpa.department.infrastructure.adapters.output.persistence.entity.DepartmentEntity;
import com.gad.spring_data_jpa.department.infrastructure.adapters.output.persistence.mapper.DepartmentMapper;
import com.gad.spring_data_jpa.department.infrastructure.adapters.output.persistence.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DepartmentPersistenceAdapter implements DepartmentPersistencePort {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public Department save(Department department) {
        DepartmentEntity departmentEntity = departmentRepository.save(departmentMapper.departmentToEntity(department));

        return departmentMapper.entityToDepartment(departmentEntity);
    }

    @Override
    public Optional<Department> findById(Long departmentId) {
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + departmentId));

        return Optional.of(departmentMapper.entityToDepartment(departmentEntity));
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::entityToDepartment)
                .toList();
    }

    @Override
    public void deleteById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Optional<Department> findByName(String name) {
        return departmentRepository.findByNameContainingIgnoreCase(name)
                .map(departmentMapper::entityToDepartment);
    }
}

package com.gad.spring_data_jpa.department.application.application.service;

import com.gad.spring_data_jpa.department.application.application.ports.input.DepartmentUseCase;
import com.gad.spring_data_jpa.department.application.application.ports.input.dto.CreateDepartmentCommand;
import com.gad.spring_data_jpa.department.application.application.ports.input.dto.DepartmentDto;
import com.gad.spring_data_jpa.department.application.application.ports.input.dto.UpdateDepartmentCommand;
import com.gad.spring_data_jpa.department.application.application.ports.output.DepartmentPersistencePort;
import com.gad.spring_data_jpa.department.domain.model.Department;
import com.gad.spring_data_jpa.department.domain.exception.DepartmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements DepartmentUseCase {
    private final DepartmentPersistencePort persistencePort;

    @Override
    public DepartmentDto createDepartment(CreateDepartmentCommand command) {
        var department = persistencePort.save(new Department(null, command.name(), command.faculty()));
        return new DepartmentDto(department.id(), department.name(), department.faculty());
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        return  persistencePort.findById(departmentId)
                .map(department -> new DepartmentDto(department.id(), department.name(), department.faculty()))
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + departmentId));
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, UpdateDepartmentCommand command) {
        var departmentDb = persistencePort.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + departmentId));
        var departmentToUpdate = departmentDb.withName(command.name())
                .withFaculty(command.faculty());
        var departmentUpdated = persistencePort.save(departmentToUpdate);
        return new DepartmentDto(departmentUpdated.id(), departmentUpdated.name(), departmentUpdated.faculty());
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return persistencePort.findAll()
                .stream()
                .map(department -> new DepartmentDto(department.id(), department.name(), department.faculty()))
                .toList();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        var department = persistencePort.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + departmentId));
        persistencePort.deleteById(department.id());
    }

    @Override
    public DepartmentDto getDepartmentByName(String departmentName) {
        var department = persistencePort.findByName(departmentName)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with name: " + departmentName));
        return new DepartmentDto(department.id(), department.name(), department.faculty());
    }
}

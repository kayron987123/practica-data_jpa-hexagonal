package com.gad.spring_data_jpa.department.infrastructure.adapters.input.rest;

import com.gad.spring_data_jpa.common.dto.response.DataResponse;
import com.gad.spring_data_jpa.common.utils.MethodUtils;
import com.gad.spring_data_jpa.department.application.application.ports.input.DepartmentUseCase;
import com.gad.spring_data_jpa.department.application.application.ports.input.dto.DepartmentDto;
import com.gad.spring_data_jpa.department.infrastructure.adapters.input.rest.mapper.DepartmentRestMapper;
import com.gad.spring_data_jpa.department.infrastructure.adapters.input.rest.model.request.CreateDepartmentRequest;
import com.gad.spring_data_jpa.department.infrastructure.adapters.input.rest.model.request.UpdateDepartmentRequest;
import com.gad.spring_data_jpa.department.infrastructure.adapters.input.rest.model.response.DepartmentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("departments")
public class DepartmentRestAdapter {
    private final DepartmentUseCase departmentUseCase;
    private final DepartmentRestMapper departmentRestMapper;

    @GetMapping
    public ResponseEntity<DataResponse<List<DepartmentResponse>>> getAllDepartments() {
        List<DepartmentDto> departmentDtoList = departmentUseCase.getAllDepartments();
        List<DepartmentResponse> departmentResponses = departmentRestMapper.dtoListToResponseList(departmentDtoList);
        DataResponse<List<DepartmentResponse>> response = DataResponse.<List<DepartmentResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Departments retrieved successfully")
                .data(departmentResponses)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<DepartmentResponse>> getDepartmentById(@PathVariable Long id) {
        DepartmentDto departmentDto = departmentUseCase.getDepartmentById(id);
        DepartmentResponse departmentResponse = departmentRestMapper.dtoToResponse(departmentDto);
        DataResponse<DepartmentResponse> response = DataResponse.<DepartmentResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Department retrieved successfully")
                .data(departmentResponse)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<DataResponse<DepartmentResponse>> getAllDepartmentsByName(@RequestParam String name) {
        DepartmentDto departmentDto = departmentUseCase.getDepartmentByName(name);
        DepartmentResponse departmentResponse = departmentRestMapper.dtoToResponse(departmentDto);
        DataResponse<DepartmentResponse> response = DataResponse.<DepartmentResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Department retrieved successfully")
                .data(departmentResponse)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DataResponse<DepartmentResponse>> createDepartment(@RequestBody @Valid CreateDepartmentRequest request) {
        DepartmentDto departmentDto = departmentUseCase.createDepartment(departmentRestMapper.toCreateDepartmentCommand(request));
        DepartmentResponse departmentResponse = departmentRestMapper.dtoToResponse(departmentDto);
        URI location = URI.create("/api/v1/departments/" + departmentDto.id());
        DataResponse<DepartmentResponse> response = DataResponse.<DepartmentResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Department created successfully")
                .data(departmentResponse)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<DepartmentResponse>> updateDepartment(@PathVariable Long id,
                                                                             @RequestBody @Valid UpdateDepartmentRequest request) {
        DepartmentDto departmentDto = departmentUseCase.updateDepartment(id, departmentRestMapper.toUpdateDepartmentCommand(request));
        DepartmentResponse departmentResponse = departmentRestMapper.dtoToResponse(departmentDto);
        DataResponse<DepartmentResponse> response = DataResponse.<DepartmentResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Department updated successfully")
                .data(departmentResponse)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteDepartment(@PathVariable Long id) {
        departmentUseCase.deleteDepartmentById(id);
        DataResponse<Void> response = DataResponse.<Void>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Department deleted successfully")
                .data(null)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}

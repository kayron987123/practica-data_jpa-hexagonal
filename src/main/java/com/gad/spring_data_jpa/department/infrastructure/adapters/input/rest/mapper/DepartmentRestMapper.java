package com.gad.spring_data_jpa.department.infrastructure.adapters.input.rest.mapper;

import com.gad.spring_data_jpa.department.application.application.ports.input.dto.CreateDepartmentCommand;
import com.gad.spring_data_jpa.department.application.application.ports.input.dto.DepartmentDto;
import com.gad.spring_data_jpa.department.application.application.ports.input.dto.UpdateDepartmentCommand;
import com.gad.spring_data_jpa.department.infrastructure.adapters.input.rest.model.request.CreateDepartmentRequest;
import com.gad.spring_data_jpa.department.infrastructure.adapters.input.rest.model.request.UpdateDepartmentRequest;
import com.gad.spring_data_jpa.department.infrastructure.adapters.input.rest.model.response.DepartmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentRestMapper {
    DepartmentResponse dtoToResponse(DepartmentDto departmentDto);

    default List<DepartmentResponse> dtoListToResponseList(List<DepartmentDto> departmentDtoList) {
        return departmentDtoList.stream()
                .map(this::dtoToResponse)
                .toList();
    }

    CreateDepartmentCommand toCreateDepartmentCommand(CreateDepartmentRequest request);
    UpdateDepartmentCommand toUpdateDepartmentCommand(UpdateDepartmentRequest request);
}

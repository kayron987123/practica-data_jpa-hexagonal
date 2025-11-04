package com.gad.spring_data_jpa.teacher.infrastructure.adapters.input.rest.mapper;

import com.gad.spring_data_jpa.teacher.application.ports.input.dto.CreateTeacherCommand;
import com.gad.spring_data_jpa.teacher.application.ports.input.dto.TeacherDto;
import com.gad.spring_data_jpa.teacher.application.ports.input.dto.UpdateTeacherCommand;
import com.gad.spring_data_jpa.teacher.infrastructure.adapters.input.rest.model.request.CreateTeacherRequest;
import com.gad.spring_data_jpa.teacher.infrastructure.adapters.input.rest.model.request.UpdateTeacherRequest;
import com.gad.spring_data_jpa.teacher.infrastructure.adapters.input.rest.model.response.TeacherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherRestMapper {
    TeacherResponse teacherDtoToResponse(TeacherDto teacherDto);

    default Page<TeacherResponse> dtoPageToResponsePage(Page<TeacherDto> teacherDtoPage) {
        return teacherDtoPage.map(this::teacherDtoToResponse);
    }

    default List<TeacherResponse> dtoListToResponseList(List<TeacherDto> teacherDtoList) {
        return teacherDtoList.stream()
                .map(this::teacherDtoToResponse)
                .toList();
    }

    CreateTeacherCommand createTeacherRequestToCommand(CreateTeacherRequest request);

    UpdateTeacherCommand updateTeacherRequestToCommand(UpdateTeacherRequest request);
}

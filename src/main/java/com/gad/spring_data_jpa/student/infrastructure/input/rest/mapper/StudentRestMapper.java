package com.gad.spring_data_jpa.student.infrastructure.input.rest.mapper;

import com.gad.spring_data_jpa.student.application.ports.input.dto.StudentDto;
import com.gad.spring_data_jpa.student.infrastructure.input.rest.model.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentRestMapper {
    StudentResponse studentDtoToStudentResponse(StudentDto studentDto);

    default Page<StudentResponse> studentDtoPageToStudentResponsePage(Page<StudentDto> studentDtoPage) {
        return studentDtoPage.map(this::studentDtoToStudentResponse);
    }
}

package com.gad.spring_data_jpa.student.infrastructure.input.rest;

import com.gad.spring_data_jpa.common.dto.response.DataResponse;
import com.gad.spring_data_jpa.common.utils.MethodUtils;
import com.gad.spring_data_jpa.student.application.ports.input.StudentUseCase;
import com.gad.spring_data_jpa.student.application.ports.input.dto.StudentDto;
import com.gad.spring_data_jpa.student.infrastructure.input.rest.mapper.StudentRestMapper;
import com.gad.spring_data_jpa.student.infrastructure.input.rest.model.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentRestAdapter {
    private final StudentUseCase studentUseCase;
    private final StudentRestMapper studentRestMapper;

    @GetMapping
    public ResponseEntity<DataResponse<Page<StudentResponse>>> getAllStudents(@PageableDefault Pageable pageable){
        Page<StudentDto> studentDtoPage = studentUseCase.getAllStudents(pageable);
        Page<StudentResponse> studentResponsePage = studentRestMapper.studentDtoPageToStudentResponsePage(studentDtoPage);

        DataResponse<Page<StudentResponse>> dataResponse = DataResponse.<Page<StudentResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Students retrieved successfully")
                .data(studentResponsePage)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();
        return ResponseEntity.ok(dataResponse);
    }
}

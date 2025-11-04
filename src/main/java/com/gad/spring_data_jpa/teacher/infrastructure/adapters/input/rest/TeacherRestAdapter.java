package com.gad.spring_data_jpa.teacher.infrastructure.adapters.input.rest;

import com.gad.spring_data_jpa.common.dto.response.DataResponse;
import com.gad.spring_data_jpa.common.utils.MethodUtils;
import com.gad.spring_data_jpa.teacher.application.ports.input.TeacherUseCase;
import com.gad.spring_data_jpa.teacher.application.ports.input.dto.TeacherDto;
import com.gad.spring_data_jpa.teacher.infrastructure.adapters.input.rest.mapper.TeacherRestMapper;
import com.gad.spring_data_jpa.teacher.infrastructure.adapters.input.rest.model.request.CreateTeacherRequest;
import com.gad.spring_data_jpa.teacher.infrastructure.adapters.input.rest.model.request.UpdateTeacherRequest;
import com.gad.spring_data_jpa.teacher.infrastructure.adapters.input.rest.model.response.TeacherResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherRestAdapter {
    private final TeacherRestMapper teacherRestMapper;
    private final TeacherUseCase teacherUseCase;

    @GetMapping
    public ResponseEntity<DataResponse<Page<TeacherResponse>>> getAllTeachersPaged(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<TeacherDto> teacherDtoPage = teacherUseCase.getAllTeachers(pageable);
        Page<TeacherResponse> teacherResponsePage = teacherRestMapper.dtoPageToResponsePage(teacherDtoPage);

        DataResponse<Page<TeacherResponse>> dataResponse = DataResponse.<Page<TeacherResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Teachers retrieved successfully")
                .data(teacherResponsePage)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<TeacherResponse>> getTeacherById(@PathVariable String id) {
        TeacherDto teacherDto = teacherUseCase.getTeacherById(Long.parseLong(id));
        TeacherResponse teacherResponse = teacherRestMapper.teacherDtoToResponse(teacherDto);

        DataResponse<TeacherResponse> dataResponse = DataResponse.<TeacherResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Teacher retrieved successfully")
                .data(teacherResponse)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/by-name")
    public ResponseEntity<DataResponse<TeacherResponse>> getTeacherByName(@RequestParam String name) {
        TeacherDto teacherDto = teacherUseCase.getTeacherByName(name);
        TeacherResponse teacherResponse = teacherRestMapper.teacherDtoToResponse(teacherDto);

        DataResponse<TeacherResponse> dataResponse = DataResponse.<TeacherResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Teacher retrieved successfully")
                .data(teacherResponse)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return ResponseEntity.ok(dataResponse);
    }


    @GetMapping("/by-department")
    public ResponseEntity<DataResponse<List<TeacherResponse>>> getTeacherByDepartmentName(@RequestParam String departmentName) {
        List<TeacherDto> teacherDtos = teacherUseCase.getTeachersByDepartmentName(departmentName);
        List<TeacherResponse> teacherResponses = teacherRestMapper.dtoListToResponseList(teacherDtos);

        DataResponse<List<TeacherResponse>> dataResponse = DataResponse.<List<TeacherResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Teachers retrieved successfully")
                .data(teacherResponses)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return ResponseEntity.ok(dataResponse);
    }


    @GetMapping("/by-email")
    public ResponseEntity<DataResponse<TeacherResponse>> getTeacherByEmail(@RequestParam String email) {
        TeacherDto teacherDto = teacherUseCase.getTeacherByEmail(email);
        TeacherResponse teacherResponse = teacherRestMapper.teacherDtoToResponse(teacherDto);

        DataResponse<TeacherResponse> dataResponse = DataResponse.<TeacherResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Teacher retrieved successfully")
                .data(teacherResponse)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping
    public ResponseEntity<DataResponse<TeacherResponse>> createTeacher(@RequestBody @Valid CreateTeacherRequest teacherRequest) {
        TeacherDto teacherDto = teacherUseCase.createTeacher(teacherRestMapper.createTeacherRequestToCommand(teacherRequest));
        TeacherResponse teacherResponse = teacherRestMapper.teacherDtoToResponse(teacherDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(teacherResponse.id())
                .toUri();

        DataResponse<TeacherResponse> dataResponse = DataResponse.<TeacherResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Teacher created successfully")
                .data(teacherResponse)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return ResponseEntity.created(location).body(dataResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<TeacherResponse>> updateTeacher(@PathVariable Long id,
                                                                       @RequestBody @Valid UpdateTeacherRequest teacherRequest) {
        TeacherDto teacherDto = teacherUseCase.updateTeacher(id, teacherRestMapper.updateTeacherRequestToCommand(teacherRequest));
        TeacherResponse teacherResponse = teacherRestMapper.teacherDtoToResponse(teacherDto);

        DataResponse<TeacherResponse> dataResponse = DataResponse.<TeacherResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Teacher updated successfully")
                .data(teacherResponse)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteTeacher(@PathVariable Long id) {
        teacherUseCase.deleteTeacher(id);
        DataResponse<Void> dataResponse = DataResponse.<Void>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Teacher deleted successfully")
                .data(null)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dataResponse);
    }
}

package com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest;

import com.gad.spring_data_jpa.common.dto.response.DataResponse;
import com.gad.spring_data_jpa.common.utils.MethodUtils;
import com.gad.spring_data_jpa.course.application.ports.input.CourseTeacherUseCase;
import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseTeacherDto;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.mapper.CourseTeacherRestMapper;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response.CourseTeacherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses-teachers")
public class CourseTeacherRestAdapter {
    private final CourseTeacherUseCase courseTeacherUseCase;
    private final CourseTeacherRestMapper courseTeacherRestMapper;

    @PostMapping("/teachers/{teacherId}/courses/{courseId}")
    public ResponseEntity<DataResponse<CourseTeacherResponse>> assignTeacherToCourse(@PathVariable Long teacherId,
                                                                                     @PathVariable Long courseId) {
        CourseTeacherDto courseTeacherDto = courseTeacherUseCase.assignCourseToTeacher(teacherId, courseId);
        CourseTeacherResponse response = courseTeacherRestMapper.courseTeacherDtoToCourseTeacherResponse(courseTeacherDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/courses-teachers/teachers/{teacherId}/courses/{courseId}")
                .buildAndExpand(teacherId, courseId)
                .toUri();

        DataResponse<CourseTeacherResponse> dataResponse = DataResponse.<CourseTeacherResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Course assigned to teacher successfully")
                .data(response)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return ResponseEntity.created(location).body(dataResponse);
    }
}

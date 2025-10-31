package com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest;

import com.gad.spring_data_jpa.common.utils.MethodUtils;
import com.gad.spring_data_jpa.course.application.ports.input.CourseUseCase;
import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseDto;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.mapper.CourseRestMapper;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.request.CreateCourseRequest;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.request.UpdateCourseRequest;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response.CourseResponse;
import com.gad.spring_data_jpa.common.dto.response.DataResponse;
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

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseRestAdapter {
    private final CourseUseCase courseUseCase;
    private final CourseRestMapper courseRestMapper;

    @GetMapping
    public ResponseEntity<DataResponse<Page<CourseResponse>>> getAllCourses(@PageableDefault(size = 5, sort = {"name"}, page = 0, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CourseDto> courseDtoPage = courseUseCase.getAllCourses(pageable);
        Page<CourseResponse> responsePage = courseRestMapper.dtoListToResponsePage(courseDtoPage);
        DataResponse<Page<CourseResponse>> dataResponse = DataResponse.<Page<CourseResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Courses retrieved successfully")
                .data(responsePage)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<CourseResponse>> getCourseById(@PathVariable Long id) {
        CourseDto courseDto = courseUseCase.getCourseById(id);
        CourseResponse response = courseRestMapper.dtoToResponse(courseDto);
        DataResponse<CourseResponse> dataResponse = DataResponse.<CourseResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Course retrieved successfully")
                .data(response)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping
    public ResponseEntity<DataResponse<CourseResponse>> createCourse(@RequestBody @Valid CreateCourseRequest request) {
        CourseDto courseDto = courseUseCase.createCourse(courseRestMapper.toCreateCourseCommand(request));
        CourseResponse response = courseRestMapper.dtoToResponse(courseDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        DataResponse<CourseResponse> dataResponse = DataResponse.<CourseResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Course created successfully")
                .data(response)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return ResponseEntity.created(location).body(dataResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<CourseResponse>> updateCourse(@PathVariable Long id, @RequestBody @Valid UpdateCourseRequest request) {
        CourseDto courseDto = courseUseCase.updateCourse(id, courseRestMapper.toUpdateCourseCommand(request));
        CourseResponse response = courseRestMapper.dtoToResponse(courseDto);
        DataResponse<CourseResponse> dataResponse = DataResponse.<CourseResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Course updated successfully")
                .data(response)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();
        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteCourse(@PathVariable Long id) {
        courseUseCase.deleteCourseById(id);
        DataResponse<Void> dataResponse = DataResponse.<Void>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Course deleted successfully")
                .data(null)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dataResponse);
    }
}

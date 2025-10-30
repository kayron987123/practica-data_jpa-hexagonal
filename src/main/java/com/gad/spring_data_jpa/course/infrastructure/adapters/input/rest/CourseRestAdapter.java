package com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest;

import com.gad.spring_data_jpa.course.application.ports.input.CourseUseCase;
import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseDto;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.mapper.CourseRestMapper;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response.CourseResponse;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response.DataResponse;
import com.gad.spring_data_jpa.course.utils.MethodUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseRestAdapter {
    private final CourseUseCase courseUseCase;
    private final CourseRestMapper courseRestMapper;

    @GetMapping
    public ResponseEntity<DataResponse<List<CourseResponse>>> getAllCourses() {
        List<CourseDto> courseDtoList = courseUseCase.getAllCourses();
        List<CourseResponse> responseList = courseRestMapper.dtoListToResponseList(courseDtoList);
        DataResponse<List<CourseResponse>> dataResponse = DataResponse.<List<CourseResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Courses retrieved successfully")
                .data(responseList)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return ResponseEntity.ok(dataResponse);
    }


    //@GetMapping("/{id}")


    //@PostMapping


    //@PutMapping("/{id}")


    //@DeleteMapping("/{id}")
}

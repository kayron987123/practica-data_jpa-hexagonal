package com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest;

import com.gad.spring_data_jpa.common.dto.response.DataResponse;
import com.gad.spring_data_jpa.common.utils.MethodUtils;
import com.gad.spring_data_jpa.course.application.ports.input.CourseTeacherUseCase;
import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseTeacherDto;
import com.gad.spring_data_jpa.course.application.ports.input.dto.CoursesTeacherList;
import com.gad.spring_data_jpa.course.application.ports.input.dto.TeachersCourseList;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.mapper.CourseTeacherRestMapper;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response.CourseTeacherResponse;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response.CoursesTeacherListResponse;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response.TeachersCourseListResponse;
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

    @PostMapping("/teacher/{teacherId}/course/{courseId}")
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

    @GetMapping("/teacher/{teacherId}/courses")
    public ResponseEntity<DataResponse<CoursesTeacherListResponse>> getCoursesByTeacher(@PathVariable Long teacherId) {
        CoursesTeacherList coursesTeacherList = courseTeacherUseCase.getCoursesByTeacherId(teacherId);
        CoursesTeacherListResponse responses = courseTeacherRestMapper.courseTeacherListToCourseTeacherListResponse(coursesTeacherList);

        DataResponse<CoursesTeacherListResponse> dataResponse = DataResponse.<CoursesTeacherListResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Courses retrieved successfully for teacher ID: " + teacherId)
                .data(responses)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/course/{courseId}/teachers")
    public ResponseEntity<DataResponse<TeachersCourseListResponse>> getTeachersByCourse(@PathVariable Long courseId) {
        TeachersCourseList teachersCourseList = courseTeacherUseCase.getTeachersByCourseId(courseId);
        TeachersCourseListResponse responses = courseTeacherRestMapper.teacherCourseListToTeacherCourseListResponse(teachersCourseList);

        DataResponse<TeachersCourseListResponse> dataResponse = DataResponse.<TeachersCourseListResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Teachers retrieved successfully for course ID: " + courseId)
                .data(responses)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();
        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/teacher/{teacherId}/course/{courseId}")
    public ResponseEntity<DataResponse<Void>> deleteCourseFromTeacher(@PathVariable Long teacherId,
                                                                      @PathVariable Long courseId) {
        courseTeacherUseCase.deleteCourseFromTeacher(courseId, teacherId);

        DataResponse<Void> dataResponse = DataResponse.<Void>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Course removed from teacher successfully")
                .data(null)
                .timestamp(MethodUtils.dateTimeNowFormatted())
                .build();

        return  ResponseEntity.noContent().build();
    }
}

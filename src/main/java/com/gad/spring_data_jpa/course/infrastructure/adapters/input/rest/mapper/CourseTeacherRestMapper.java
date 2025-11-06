package com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.mapper;

import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseTeacherDto;
import com.gad.spring_data_jpa.course.application.ports.input.dto.CoursesTeacherList;
import com.gad.spring_data_jpa.course.application.ports.input.dto.TeachersCourseDataList;
import com.gad.spring_data_jpa.course.application.ports.input.dto.TeachersCourseList;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response.CourseTeacherResponse;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response.CoursesTeacherListResponse;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response.TeachersCourseListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseTeacherRestMapper {
    CourseTeacherResponse courseTeacherDtoToCourseTeacherResponse(CourseTeacherDto courseTeacherDto);

    default List<CourseTeacherResponse> courseTeacherDtoListToCourseTeacherResponseList(List<CourseTeacherDto> courseTeacherDtos) {
        return courseTeacherDtos.stream()
                .map(this::courseTeacherDtoToCourseTeacherResponse)
                .toList();
    }

    CoursesTeacherListResponse courseTeacherListToCourseTeacherListResponse(CoursesTeacherList coursesTeacherList);

    TeachersCourseListResponse teacherCourseListToTeacherCourseListResponse(TeachersCourseList teachersCourseDataList);
}

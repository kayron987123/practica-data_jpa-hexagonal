package com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.mapper;

import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseTeacherDto;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response.CourseTeacherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseTeacherRestMapper {
    CourseTeacherResponse courseTeacherDtoToCourseTeacherResponse(CourseTeacherDto courseTeacherDto);
}

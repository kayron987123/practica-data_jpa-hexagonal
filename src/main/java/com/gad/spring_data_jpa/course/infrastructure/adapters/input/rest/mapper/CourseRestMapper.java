package com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.mapper;

import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseDto;
import com.gad.spring_data_jpa.course.application.ports.input.dto.CreateCourseCommand;
import com.gad.spring_data_jpa.course.application.ports.input.dto.UpdateCourseCommand;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.request.CreateCourseRequest;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.request.UpdateCourseRequest;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response.CourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseRestMapper {
    CourseResponse dtoToResponse(CourseDto courseDto);

    default Page<CourseResponse> dtoListToResponsePage(Page<CourseDto> courseDtoList){
        return courseDtoList.map(this::dtoToResponse);
    }

    CreateCourseCommand toCreateCourseCommand(CreateCourseRequest request);
    UpdateCourseCommand toUpdateCourseCommand(UpdateCourseRequest request);
}

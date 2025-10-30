package com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.mapper;

import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseDto;
import com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response.CourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseRestMapper {
    CourseResponse dtoToResponse(CourseDto courseDto);

    default List<CourseResponse> dtoListToResponseList(List<CourseDto> courseDtoList){
        return courseDtoList.stream()
                .map(this::dtoToResponse)
                .toList();
    }
}

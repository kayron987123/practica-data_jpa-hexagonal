package com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.mapper;

import com.gad.spring_data_jpa.course.domain.model.Course;
import com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {
    @Mapping(target = "department", ignore = true)
    CourseEntity courseToEntity(Course course);

    @Mapping(target = "departmentName", source = "department.name")
    Course entityToCourse(CourseEntity courseEntity);
}

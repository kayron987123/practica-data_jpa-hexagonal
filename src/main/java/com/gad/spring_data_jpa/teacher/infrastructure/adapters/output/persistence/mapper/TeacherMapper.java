package com.gad.spring_data_jpa.teacher.infrastructure.adapters.output.persistence.mapper;

import com.gad.spring_data_jpa.teacher.domain.model.Teacher;
import com.gad.spring_data_jpa.teacher.infrastructure.adapters.output.persistence.entity.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper {

    @Mapping(target = "department", ignore = true)
    TeacherEntity teacherModelToEntity(Teacher teacher);

    @Mapping(target = "departmentName", source = "department.name")
    Teacher teacherEntityToModel(TeacherEntity teacher);
}

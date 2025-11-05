package com.gad.spring_data_jpa.student.infrastructure.output.persistence.mapper;

import com.gad.spring_data_jpa.student.domain.model.Student;
import com.gad.spring_data_jpa.student.infrastructure.output.persistence.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {
    @Mapping(target = "id", ignore = true)
    StudentEntity studentToStudentEntity(Student student);
    Student studentEntityToStudent(StudentEntity studentEntity);
}

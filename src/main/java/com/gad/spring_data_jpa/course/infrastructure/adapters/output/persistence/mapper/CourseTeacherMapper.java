package com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.mapper;

import com.gad.spring_data_jpa.course.domain.model.CourseTeacherAssignment;
import com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.entity.CourseEntity;
import com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.entity.CourseTeacherEntity;
import com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.entity.CourseTeacherId;
import com.gad.spring_data_jpa.teacher.infrastructure.adapters.output.persistence.entity.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseTeacherMapper {
    default CourseTeacherEntity courseTeacherModelToCourseTeacherEntity(CourseTeacherAssignment assignment) {
        if (assignment == null) return null;

        CourseTeacherEntity entity = new CourseTeacherEntity();

        // Asignamos IDs embebidos
        var id = new CourseTeacherId();
        id.setCourseId(assignment.courseId());
        id.setTeacherId(assignment.teacherId());
        entity.setId(id);

        // Asignamos entidades con solo el ID
        var course = new CourseEntity();
        course.setId(assignment.courseId());

        var teacher = new TeacherEntity();
        teacher.setId(assignment.teacherId());

        entity.setCourse(course);
        entity.setTeacher(teacher);
        entity.setAssignedDate(assignment.assignedDate());

        return entity;
    }

    @Mapping(target = "courseId", source = "courseTeacherEntity.id.courseId")
    @Mapping(target = "teacherId", source = "courseTeacherEntity.id.teacherId")
    CourseTeacherAssignment courseTeacherEntityToCourseTeacherModel(CourseTeacherEntity courseTeacherEntity);
}

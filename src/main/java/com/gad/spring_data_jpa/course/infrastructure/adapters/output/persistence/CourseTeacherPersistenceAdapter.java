package com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence;

import com.gad.spring_data_jpa.course.application.ports.output.CourseTeacherPersistencePort;
import com.gad.spring_data_jpa.course.domain.model.CourseTeacherAssignment;
import com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.entity.CourseTeacherEntity;
import com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.repository.CourseTeacherRepository;
import com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.mapper.CourseTeacherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CourseTeacherPersistenceAdapter implements CourseTeacherPersistencePort {
    private final CourseTeacherRepository courseTeacherRepository;
    private final CourseTeacherMapper courseTeacherMapper;

    @Override
    public CourseTeacherAssignment createCourseTeacherAssignment(CourseTeacherAssignment courseTeacherAssignment) {
        CourseTeacherEntity courseTeacherEntity = courseTeacherRepository
                .save(courseTeacherMapper.courseTeacherModelToCourseTeacherEntity(courseTeacherAssignment));
        log.info(courseTeacherEntity.toString());
        return courseTeacherMapper.courseTeacherEntityToCourseTeacherModel(courseTeacherEntity);
    }

    @Override
    public List<CourseTeacherAssignment> findCoursesAssignedToTeachers(Long teacherId) {
        return courseTeacherRepository.findCoursesAssignedToTeachers(teacherId)
                .stream()
                .map(courseTeacherMapper::courseTeacherEntityToCourseTeacherModel)
                .toList();
    }

    @Override
    public List<CourseTeacherAssignment> findTeachersAssignedToCourse(Long courseId) {
        return courseTeacherRepository.findTeachersAssignedToCourse(courseId)
                .stream()
                .map(courseTeacherMapper::courseTeacherEntityToCourseTeacherModel)
                .toList();
    }

    @Override
    public void deleteCourseTeacherAssignment(Long courseId, Long teacherId) {
        courseTeacherRepository.deleteById_CourseIdAndId_TeacherId(courseId, teacherId);
    }
}

package com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.repository;

import com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.entity.CourseTeacherEntity;
import com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.entity.CourseTeacherId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseTeacherRepository extends JpaRepository<CourseTeacherEntity, CourseTeacherId> {

    @Query("""
            SELECT cte 
            FROM CourseTeacherEntity cte
            WHERE cte.course.id = :courseId
            """)
    List<CourseTeacherEntity> findTeachersAssignedToCourse(Long courseId);


    @Query("""
            SELECT cte 
            FROM CourseTeacherEntity cte
            WHERE cte.teacher.id = :teacherId
            """)
    List<CourseTeacherEntity> findCoursesAssignedToTeachers(Long teacherId);

    void deleteById_CourseIdAndId_TeacherId(Long courseId, Long teacherId);
}

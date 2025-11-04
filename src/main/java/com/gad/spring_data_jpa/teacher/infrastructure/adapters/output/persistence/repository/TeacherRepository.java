package com.gad.spring_data_jpa.teacher.infrastructure.adapters.output.persistence.repository;

import com.gad.spring_data_jpa.teacher.infrastructure.adapters.output.persistence.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    List<TeacherEntity> findTeacherEntitiesByDepartmentName(String departmentName);
    TeacherEntity findTeacherEntityByFullNameContainingIgnoreCase(String fullName);
    TeacherEntity findTeacherEntityByEmailIgnoreCase(String email);
}

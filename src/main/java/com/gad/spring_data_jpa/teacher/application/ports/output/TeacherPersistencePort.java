package com.gad.spring_data_jpa.teacher.application.ports.output;

import com.gad.spring_data_jpa.teacher.domain.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TeacherPersistencePort {
    Teacher save(Teacher teacher);
    Optional<Teacher>  findById(Long teacherId);
    void deleteById(Long teacherId);
    Page<Teacher> findAll(Pageable pageable);
    List<Teacher> findTeachersByDepartmentName(String departmentName);
    Optional<Teacher>  findTeacherByName(String name);
    Optional<Teacher>  findTeacherByEmail(String email);
}

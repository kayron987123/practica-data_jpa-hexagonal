package com.gad.spring_data_jpa.teacher.application.ports.input;

import com.gad.spring_data_jpa.teacher.application.ports.input.dto.CreateTeacherCommand;
import com.gad.spring_data_jpa.teacher.application.ports.input.dto.TeacherDto;
import com.gad.spring_data_jpa.teacher.application.ports.input.dto.UpdateTeacherCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TeacherUseCase {
    TeacherDto createTeacher(CreateTeacherCommand command);
    Page<TeacherDto> getAllTeachers(Pageable pageable);
    TeacherDto getTeacherById(Long teacherId);
    TeacherDto updateTeacher(Long teacherId, UpdateTeacherCommand command);
    void deleteTeacher(Long teacherId);
    List<TeacherDto> getTeachersByDepartmentName(String departmentName);
    TeacherDto getTeacherByName(String name);
    TeacherDto getTeacherByEmail(String email);
}

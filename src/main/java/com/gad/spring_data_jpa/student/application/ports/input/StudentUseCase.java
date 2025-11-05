package com.gad.spring_data_jpa.student.application.ports.input;

import com.gad.spring_data_jpa.student.application.ports.input.dto.CreateStudentCommand;
import com.gad.spring_data_jpa.student.application.ports.input.dto.StudentDto;
import com.gad.spring_data_jpa.student.application.ports.input.dto.UpdateStudentCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentUseCase {
    StudentDto createStudent(CreateStudentCommand command);
    StudentDto updateStudent(Long studentId, UpdateStudentCommand command);
    StudentDto getStudentById(Long studentId);
    void deleteStudentById(Long studentId);
    Page<StudentDto> getAllStudents(Pageable pageable);
    StudentDto getStudentByEmail(String email);
    StudentDto getStudentByFullName(String fullName);
}

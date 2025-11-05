package com.gad.spring_data_jpa.student.application.service;

import com.gad.spring_data_jpa.student.application.ports.input.StudentUseCase;
import com.gad.spring_data_jpa.student.application.ports.input.dto.CreateStudentCommand;
import com.gad.spring_data_jpa.student.application.ports.input.dto.StudentDto;
import com.gad.spring_data_jpa.student.application.ports.input.dto.UpdateStudentCommand;
import com.gad.spring_data_jpa.student.application.ports.output.StudentPersistencePort;
import com.gad.spring_data_jpa.student.domain.exception.StudentNotFoundException;
import com.gad.spring_data_jpa.student.domain.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StudentService implements StudentUseCase {
    private final StudentPersistencePort persistencePort;

    @Override
    public StudentDto createStudent(CreateStudentCommand command) {
        Student student = persistencePort.createStudent(Student.create(command.fullName(), command.email(), LocalDate.now().getYear()));

        return StudentDto.builder()
                .fullName(student.fullName())
                .email(student.email())
                .enrollmentYear(student.enrollmentYear())
                .build();
    }

    @Override
    public StudentDto updateStudent(Long studentId, UpdateStudentCommand command) {
        Student student = persistencePort.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + studentId + " not found"));

        student.withEmail(command.email())
                .withFullName(command.fullName())
                .withEnrollmentYear(LocalDate.now().getYear());

        Student studentSaved = persistencePort.createStudent(student);

        return StudentDto.builder()
                .fullName(studentSaved.fullName())
                .email(studentSaved.email())
                .enrollmentYear(studentSaved.enrollmentYear())
                .build();
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = persistencePort.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + studentId + " not found"));

        return StudentDto.builder()
                .fullName(student.fullName())
                .email(student.email())
                .enrollmentYear(student.enrollmentYear())
                .build();
    }

    @Override
    public void deleteStudentById(Long studentId) {
        persistencePort.deleteById(studentId);
    }

    @Override
    public Page<StudentDto> getAllStudents(Pageable pageable) {
        return persistencePort.findAll(pageable)
                .map(student -> StudentDto.builder()
                        .fullName(student.fullName())
                        .email(student.email())
                        .enrollmentYear(student.enrollmentYear())
                        .build());
    }

    @Override
    public StudentDto getStudentByEmail(String email) {
        Student student = persistencePort.findByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException("Student with email " + email + " not found"));

        return StudentDto.builder()
                .fullName(student.fullName())
                .email(student.email())
                .enrollmentYear(student.enrollmentYear())
                .build();
    }

    @Override
    public StudentDto getStudentByFullName(String fullName) {
        Student student = persistencePort.findByFullName(fullName)
                .orElseThrow(() -> new StudentNotFoundException("Student with full name " + fullName + " not found"));

        return StudentDto.builder()
                .fullName(student.fullName())
                .email(student.email())
                .enrollmentYear(student.enrollmentYear())
                .build();
    }
}

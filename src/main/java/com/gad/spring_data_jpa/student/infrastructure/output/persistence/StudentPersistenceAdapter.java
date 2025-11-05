package com.gad.spring_data_jpa.student.infrastructure.output.persistence;

import com.gad.spring_data_jpa.student.application.ports.output.StudentPersistencePort;
import com.gad.spring_data_jpa.student.domain.exception.StudentNotFoundException;
import com.gad.spring_data_jpa.student.domain.model.Student;
import com.gad.spring_data_jpa.student.infrastructure.output.persistence.entity.StudentEntity;
import com.gad.spring_data_jpa.student.infrastructure.output.persistence.mapper.StudentMapper;
import com.gad.spring_data_jpa.student.infrastructure.output.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentPersistenceAdapter implements StudentPersistencePort {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public Student createStudent(Student student) {
        StudentEntity studentEntity = studentRepository.save(studentMapper.studentToStudentEntity(student));
        return studentMapper.studentEntityToStudent(studentEntity);
    }

    @Override
    public Optional<Student> findById(Long studentId) {
        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + studentId));

        return Optional.of(studentMapper.studentEntityToStudent(studentEntity));

    }

    @Override
    public Optional<Student> findByEmail(String email) {
        StudentEntity studentEntity = studentRepository.findByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with email: " + email));

        return Optional.of(studentMapper.studentEntityToStudent(studentEntity));
    }

    @Override
    public Optional<Student> findByFullName(String fullName) {
        StudentEntity studentEntity = studentRepository.findByFullNameContainingIgnoreCase(fullName)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with full name: " + fullName));

        return Optional.of(studentMapper.studentEntityToStudent(studentEntity));
    }

    @Override
    public void deleteById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(studentMapper::studentEntityToStudent);
    }
}

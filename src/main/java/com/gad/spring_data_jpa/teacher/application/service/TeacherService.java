package com.gad.spring_data_jpa.teacher.application.service;

import com.gad.spring_data_jpa.common.utils.MethodUtils;
import com.gad.spring_data_jpa.teacher.application.ports.input.TeacherUseCase;
import com.gad.spring_data_jpa.teacher.application.ports.input.dto.CreateTeacherCommand;
import com.gad.spring_data_jpa.teacher.application.ports.input.dto.TeacherDto;
import com.gad.spring_data_jpa.teacher.application.ports.input.dto.UpdateTeacherCommand;
import com.gad.spring_data_jpa.teacher.application.ports.output.TeacherPersistencePort;
import com.gad.spring_data_jpa.teacher.domain.exception.TeacherNotFoundException;
import com.gad.spring_data_jpa.teacher.domain.model.Teacher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService implements TeacherUseCase {
    private final TeacherPersistencePort teacherPersistencePort;

    @Override
    public TeacherDto createTeacher(CreateTeacherCommand command) {
        Teacher teacher = Teacher.create(null, command.fullName(), command.email(), LocalDate.now(), command.departmentName());

        Teacher teacherSaved = teacherPersistencePort.save(teacher);

        return new TeacherDto(teacherSaved.id(), teacherSaved.fullName(), teacherSaved.email(), teacherSaved.hireDate(), teacherSaved.departmentName());
    }

    @Override
    public Page<TeacherDto> getAllTeachers(Pageable pageable) {
        return teacherPersistencePort.findAll(pageable)
                .map(teacher -> new TeacherDto(teacher.id(), teacher.fullName(), teacher.email(), teacher.hireDate(), teacher.departmentName()));
    }

    @Override
    public TeacherDto getTeacherById(Long teacherId) {
        Teacher teacherFound = teacherPersistencePort.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + teacherId));

        return new TeacherDto(teacherFound.id(), teacherFound.fullName(), teacherFound.email(), teacherFound.hireDate(), teacherFound.departmentName());
    }

    @Override
    public TeacherDto updateTeacher(Long teacherId, UpdateTeacherCommand command) {
        Teacher teacherFound = teacherPersistencePort.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + teacherId));

        log.info("Command: {}", command.toString());

        teacherFound = Teacher.builder()
                .id(teacherFound.id())
                .fullName(command.fullName())
                .email(command.email())
                .hireDate(LocalDate.now())
                .departmentName(command.departmentName())
                .build();

        log.info("Updating teacher: {}", teacherFound.toString());

        Teacher teacherSaved = teacherPersistencePort.save(teacherFound);
        return new TeacherDto(teacherSaved.id(), teacherSaved.fullName(), teacherSaved.email(), teacherSaved.hireDate(), teacherSaved.departmentName());
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        Teacher teacherFound = teacherPersistencePort.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + teacherId));

        teacherPersistencePort.deleteById(teacherFound.id());
    }

    @Override
    public List<TeacherDto> getTeachersByDepartmentName(String departmentName) {
        return teacherPersistencePort.findTeachersByDepartmentName(departmentName)
                .stream()
                .map(teacher -> new TeacherDto(teacher.id(), teacher.fullName(), teacher.email(), teacher.hireDate(), teacher.departmentName()))
                .toList();
    }

    @Override
    public TeacherDto getTeacherByName(String name) {
        return teacherPersistencePort.findTeacherByName(name)
                .map(teacher -> new TeacherDto(teacher.id(), teacher.fullName(), teacher.email(), teacher.hireDate(), teacher.departmentName()))
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with name: " + name));
    }

    @Override
    public TeacherDto getTeacherByEmail(String email) {
        return teacherPersistencePort.findTeacherByEmail(email)
                .map(teacher -> new TeacherDto(teacher.id(), teacher.fullName(), teacher.email(), teacher.hireDate(), teacher.departmentName()))
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with email: " + email));
    }
}

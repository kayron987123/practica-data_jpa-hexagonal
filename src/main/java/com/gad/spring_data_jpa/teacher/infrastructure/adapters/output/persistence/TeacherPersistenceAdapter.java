package com.gad.spring_data_jpa.teacher.infrastructure.adapters.output.persistence;

import com.gad.spring_data_jpa.department.domain.exception.DepartmentNotFoundException;
import com.gad.spring_data_jpa.department.infrastructure.adapters.output.persistence.entity.DepartmentEntity;
import com.gad.spring_data_jpa.department.infrastructure.adapters.output.persistence.repository.DepartmentRepository;
import com.gad.spring_data_jpa.teacher.application.ports.output.TeacherPersistencePort;
import com.gad.spring_data_jpa.teacher.domain.exception.TeacherNotFoundException;
import com.gad.spring_data_jpa.teacher.domain.model.Teacher;
import com.gad.spring_data_jpa.teacher.infrastructure.adapters.output.persistence.entity.TeacherEntity;
import com.gad.spring_data_jpa.teacher.infrastructure.adapters.output.persistence.mapper.TeacherMapper;
import com.gad.spring_data_jpa.teacher.infrastructure.adapters.output.persistence.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeacherPersistenceAdapter implements TeacherPersistencePort {
    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;
    private final TeacherMapper mapper;

    @Override
    public Teacher save(Teacher teacher) {
        DepartmentEntity department = departmentRepository.findByNameContainingIgnoreCase(teacher.departmentName())
                .orElseThrow(() -> new DepartmentNotFoundException("Department with name " + teacher.departmentName() + " not found"));

        log.info("Department found: {}", department.getName());
        TeacherEntity teacherEntity = mapper.teacherModelToEntity(teacher);
        teacherEntity.setDepartment(department);

        log.info("Teacher found: {}", teacherEntity.getFullName());
        TeacherEntity teacherSaved = teacherRepository.save(teacherEntity);
        log.info("Teacher saved: {}, {}", teacherSaved.getFullName(), teacherSaved.getDepartment().getName());
        return mapper.teacherEntityToModel(teacherSaved);
    }

    @Override
    public Optional<Teacher> findById(Long teacherId) {
        TeacherEntity teacherEntity = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with id " + teacherId + " not found"));
        return Optional.of(mapper.teacherEntityToModel(teacherEntity));
    }

    @Override
    public void deleteById(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    @Override
    public Page<Teacher> findAll(Pageable pageable) {
        return teacherRepository.findAll(pageable)
                .map(mapper::teacherEntityToModel);
    }

    @Override
    public List<Teacher> findTeachersByDepartmentName(String departmentName) {
        return teacherRepository.findTeacherEntitiesByDepartmentName(departmentName)
                .stream()
                .map(mapper::teacherEntityToModel)
                .toList();
    }

    @Override
    public Optional<Teacher> findTeacherByName(String name) {
        TeacherEntity teacher = teacherRepository.findTeacherEntityByFullNameContainingIgnoreCase(name);
        return Optional.of(mapper.teacherEntityToModel(teacher));
    }

    @Override
    public Optional<Teacher> findTeacherByEmail(String email) {
        TeacherEntity teacher = teacherRepository.findTeacherEntityByEmailIgnoreCase(email);
        return Optional.of(mapper.teacherEntityToModel(teacher));
    }
}

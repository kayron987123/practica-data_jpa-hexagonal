package com.gad.spring_data_jpa.course.application.service;

import com.gad.spring_data_jpa.course.application.ports.input.CourseTeacherUseCase;
import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseTeacherDto;
import com.gad.spring_data_jpa.course.application.ports.output.CoursePersistencePort;
import com.gad.spring_data_jpa.course.application.ports.output.CourseTeacherPersistencePort;
import com.gad.spring_data_jpa.course.domain.exception.CourseNotFoundException;
import com.gad.spring_data_jpa.course.domain.model.Course;
import com.gad.spring_data_jpa.course.domain.model.CourseTeacherAssignment;
import com.gad.spring_data_jpa.teacher.application.ports.output.TeacherPersistencePort;
import com.gad.spring_data_jpa.teacher.domain.exception.TeacherNotFoundException;
import com.gad.spring_data_jpa.teacher.domain.model.Teacher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseTeacherService implements CourseTeacherUseCase {
    private final CourseTeacherPersistencePort courseTeacherPersistencePort;
    private final CoursePersistencePort coursePersistencePort;
    private final TeacherPersistencePort teacherPersistencePort;

    @Override
    public CourseTeacherDto assignCourseToTeacher(Long teacherId, Long courseId) {
        return assignCourseAndTeacher(teacherId, courseId);
    }

    @Override
    public CourseTeacherDto updateCourseToTeacher(Long teacherId, Long courseId) {
        return assignCourseAndTeacher(teacherId, courseId);
    }

    private CourseTeacherDto assignCourseAndTeacher(Long teacherId, Long courseId) {
        Course course = coursePersistencePort.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with id " + courseId + " not found"));

        Teacher teacher = teacherPersistencePort.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with id " + teacherId + " not found"));

        CourseTeacherAssignment courseTeacherAssignment = CourseTeacherAssignment.builder()
                .courseId(courseId)
                .teacherId(teacherId)
                .assignedDate(LocalDate.now())
                .build();

        log.info(courseTeacherAssignment.toString());

        CourseTeacherAssignment courseTeacherUpdated = courseTeacherPersistencePort.createCourseTeacherAssignment(courseTeacherAssignment);

        log.info(courseTeacherUpdated.toString());

        return CourseTeacherDto.builder()
                .courseId(courseTeacherUpdated.courseId())
                .teacherId(courseTeacherUpdated.teacherId())
                .courseName(course.name())
                .teacherName(teacher.fullName())
                .credits(course.credits())
                .department(course.departmentName())
                .build();
    }

    @Override
    public void deleteCourseFromTeacher(Long idCourse, Long idTeacher) {
        Course course = coursePersistencePort.findById(idCourse)
                .orElseThrow(() -> new CourseNotFoundException("Course with id " + idCourse + " not found"));

        Teacher teacher = teacherPersistencePort.findById(idTeacher)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with id " + idTeacher + " not found"));

        courseTeacherPersistencePort.deleteCourseTeacherAssignment(course.id(), teacher.id());
    }

    @Override
    public List<CourseTeacherDto> getCoursesByTeacherId(Long teacherId) {
        return courseTeacherPersistencePort.findCoursesAssignedToTeachers(teacherId)
                .stream()
                .map(courseTeacherAssignment -> {
                    Course course = coursePersistencePort.findById(courseTeacherAssignment.courseId())
                            .orElseThrow(() -> new CourseNotFoundException("Course with id " + courseTeacherAssignment.courseId() + " not found"));

                    Teacher teacher = teacherPersistencePort.findById(courseTeacherAssignment.teacherId())
                            .orElseThrow(() -> new TeacherNotFoundException("Teacher with id " + courseTeacherAssignment.teacherId() + " not found"));

                    return CourseTeacherDto.builder()
                            .courseId(course.id())
                            .teacherId(teacher.id())
                            .courseName(course.name())
                            .teacherName(teacher.fullName())
                            .credits(course.credits())
                            .department(course.departmentName())
                            .build();
                }).toList();
    }

    @Override
    public List<CourseTeacherDto> getTeachersByCourseId(Long courseId) {
        return courseTeacherPersistencePort.findTeachersAssignedToCourse(courseId)
                .stream()
                .map(courseTeacherAssignment -> {
                    Course course = coursePersistencePort.findById(courseTeacherAssignment.courseId())
                            .orElseThrow(() -> new CourseNotFoundException("Course with id " + courseTeacherAssignment.courseId() + " not found"));

                    Teacher teacher = teacherPersistencePort.findById(courseTeacherAssignment.teacherId())
                            .orElseThrow(() -> new TeacherNotFoundException("Teacher with id " + courseTeacherAssignment.teacherId() + " not found"));

                    return CourseTeacherDto.builder()
                            .courseId(course.id())
                            .teacherId(teacher.id())
                            .courseName(course.name())
                            .teacherName(teacher.fullName())
                            .credits(course.credits())
                            .department(course.departmentName())
                            .build();
                }).toList();
    }
}

package com.gad.spring_data_jpa.course.application.service;

import com.gad.spring_data_jpa.course.application.ports.input.CourseTeacherUseCase;
import com.gad.spring_data_jpa.course.application.ports.input.dto.*;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseTeacherService implements CourseTeacherUseCase {
    private final CourseTeacherPersistencePort courseTeacherPersistencePort;
    private final CoursePersistencePort coursePersistencePort;
    private final TeacherPersistencePort teacherPersistencePort;

    @Transactional
    @Override
    public CourseTeacherDto assignCourseToTeacher(Long teacherId, Long courseId) {
        return assignCourseAndTeacher(teacherId, courseId);
    }

    @Transactional
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

    @Transactional
    @Override
    public void deleteCourseFromTeacher(Long idCourse, Long idTeacher) {
        Course course = coursePersistencePort.findById(idCourse)
                .orElseThrow(() -> new CourseNotFoundException("Course with id " + idCourse + " not found"));

        Teacher teacher = teacherPersistencePort.findById(idTeacher)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with id " + idTeacher + " not found"));

        courseTeacherPersistencePort.deleteCourseTeacherAssignment(course.id(), teacher.id());
    }

    @Override
    public CoursesTeacherList getCoursesByTeacherId(Long teacherId) {
        Teacher teacher = teacherPersistencePort.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with id " + teacherId + " not found"));

        List<CoursesTeacherDataList> courseDataList = courseTeacherPersistencePort.findCoursesAssignedToTeachers(teacherId)
                .stream()
                .map(courseTeacherAssignment -> {
                    Course course = coursePersistencePort.findById(courseTeacherAssignment.courseId())
                            .orElseThrow(() -> new CourseNotFoundException("Course with id " + courseTeacherAssignment.courseId() + " not found"));

                    return CoursesTeacherDataList.builder()
                            .courseId(course.id())
                            .courseName(course.name())
                            .credits(course.credits())
                            .departmentName(course.departmentName())
                            .build();
                }).toList();

        return CoursesTeacherList.builder()
                .teacherId(teacher.id())
                .teacherName(teacher.fullName())
                .coursesDataList(courseDataList)
                .build();
    }

    @Override
    public TeachersCourseList getTeachersByCourseId(Long courseId) {
        Course course = coursePersistencePort.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with id " + courseId + " not found"));

        List<TeachersCourseDataList> teacherDataList= courseTeacherPersistencePort.findTeachersAssignedToCourse(courseId)
                .stream()
                .map(courseTeacherAssignment -> {
                    Teacher teacher = teacherPersistencePort.findById(courseTeacherAssignment.teacherId())
                            .orElseThrow(() -> new TeacherNotFoundException("Teacher with id " + courseTeacherAssignment.teacherId() + " not found"));

                    return TeachersCourseDataList.builder()
                            .teacherId(teacher.id())
                            .teacherName(teacher.fullName())
                            .build();
                }).toList();

        return TeachersCourseList.builder()
                .courseId(course.id())
                .courseName(course.name())
                .credits(course.credits())
                .departmentName(course.departmentName())
                .teachersCourseDataLists(teacherDataList)
                .build();
    }
}

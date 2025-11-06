CREATE TABLE departments
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    faculty VARCHAR(100) NOT NULL
);

CREATE TABLE teachers
(
    id            SERIAL PRIMARY KEY,
    full_name     VARCHAR(150)        NOT NULL,
    email         VARCHAR(100) UNIQUE NOT NULL,
    hire_date     DATE                NOT NULL,
    department_id INT                 NOT NULL REFERENCES departments (id)
);

CREATE TABLE students
(
    id              SERIAL PRIMARY KEY,
    full_name       VARCHAR(150)        NOT NULL,
    email           VARCHAR(100) UNIQUE NOT NULL,
    enrollment_year INT                 NOT NULL
);

CREATE TABLE courses
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(150) NOT NULL,
    credits       INT          NOT NULL CHECK (credits > 0),
    department_id INT          NOT NULL REFERENCES departments (id)
);

-- Tabla intermedia entre curso y profesor (un curso puede tener varios profesores)
CREATE TABLE course_teachers
(
    course_id     INT  NOT NULL,
    teacher_id    INT  NOT NULL,
    assigned_date DATE NOT NULL,
    PRIMARY KEY (course_id, teacher_id),
    FOREIGN KEY (course_id) REFERENCES courses (id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (id)
);

-- Tabla intermedia entre curso y estudiante, con atributos extra
CREATE TABLE enrollments
(
    student_id    INT         NOT NULL,
    course_id     INT         NOT NULL,
    semester      VARCHAR(10) NOT NULL,
    enrolled_date DATE        NOT NULL DEFAULT CURRENT_DATE,
    grade         NUMERIC(4, 2),
    PRIMARY KEY (student_id, course_id, semester),
    FOREIGN KEY (student_id) REFERENCES students (id),
    FOREIGN KEY (course_id) REFERENCES courses (id)
);

-- Cada curso tiene evaluaciones (parciales, finales, etc.)
CREATE TABLE exams
(
    id        SERIAL PRIMARY KEY,
    course_id INT          NOT NULL REFERENCES courses (id),
    title     VARCHAR(100) NOT NULL,
    exam_date DATE         NOT NULL
);

-- Tabla intermedia entre examen y estudiante (registro de notas)
CREATE TABLE exam_results
(
    exam_id    INT           NOT NULL,
    student_id INT           NOT NULL,
    score      NUMERIC(4, 2) NOT NULL CHECK (score >= 0 AND score <= 20),
    PRIMARY KEY (exam_id, student_id),
    FOREIGN KEY (exam_id) REFERENCES exams (id),
    FOREIGN KEY (student_id) REFERENCES students (id)
);

INSERT INTO departments (name, faculty)
VALUES ('Computer Science', 'Engineering'),
       ('Electrical Engineering', 'Engineering'),
       ('Mechanical Engineering', 'Engineering'),
       ('Civil Engineering', 'Engineering'),
       ('Architecture', 'Architecture & Design'),
       ('Economics', 'Business'),
       ('Marketing', 'Business'),
       ('Psychology', 'Humanities'),
       ('Law', 'Law & Political Science'),
       ('Medicine', 'Health Sciences');

INSERT INTO teachers (full_name, email, hire_date, department_id)
VALUES ('Dr. Alice Johnson', 'alice.johnson@university.edu', '2015-03-15', 1),
       ('Dr. Brian Smith', 'brian.smith@university.edu', '2018-07-10', 1),
       ('Eng. Carlos Torres', 'carlos.torres@university.edu', '2012-01-25', 2),
       ('Dr. Daniela Rojas', 'daniela.rojas@university.edu', '2017-05-20', 3),
       ('Ing. Eduardo Ramos', 'eduardo.ramos@university.edu', '2014-09-02', 4),
       ('Arq. Fernanda Díaz', 'fernanda.diaz@university.edu', '2016-11-11', 5),
       ('Dr. Gustavo Pérez', 'gustavo.perez@university.edu', '2013-02-08', 6),
       ('Lic. Helena Ruiz', 'helena.ruiz@university.edu', '2019-04-22', 7),
       ('Dr. Iván Mendoza', 'ivan.mendoza@university.edu', '2011-08-19', 8),
       ('Dr. Julia Vargas', 'julia.vargas@university.edu', '2010-12-03', 9);

INSERT INTO students (full_name, email, enrollment_year)
VALUES ('Andrés Castillo', 'andres.castillo@student.edu', 2022),
       ('Beatriz Flores', 'beatriz.flores@student.edu', 2021),
       ('Carlos Navarro', 'carlos.navarro@student.edu', 2023),
       ('Diana Ramos', 'diana.ramos@student.edu', 2020),
       ('Eduardo García', 'eduardo.garcia@student.edu', 2024),
       ('Fernanda Paredes', 'fernanda.paredes@student.edu', 2023),
       ('Gabriel Soto', 'gabriel.soto@student.edu', 2021),
       ('Helena Quiroz', 'helena.quiroz@student.edu', 2022),
       ('Ignacio Torres', 'ignacio.torres@student.edu', 2024),
       ('Julia Salazar', 'julia.salazar@student.edu', 2020);

INSERT INTO courses (name, credits, department_id)
VALUES ('Data Structures and Algorithms', 4, 1),
       ('Database Systems', 3, 1),
       ('Circuit Analysis', 4, 2),
       ('Thermodynamics', 3, 3),
       ('Structural Engineering', 5, 4),
       ('Architectural Design I', 4, 5),
       ('Microeconomics', 3, 6),
       ('Digital Marketing', 3, 7),
       ('Cognitive Psychology', 4, 8),
       ('Constitutional Law', 5, 9);

INSERT INTO course_teachers (course_id, teacher_id, assigned_date)
VALUES (1, 1, '2022-03-01'),
       (2, 2, '2022-03-01'),
       (3, 3, '2022-03-15'),
       (4, 4, '2022-03-20'),
       (5, 5, '2022-04-01'),
       (6, 6, '2022-04-10'),
       (7, 7, '2022-04-15'),
       (8, 8, '2022-04-20'),
       (9, 9, '2022-04-25'),
       (10, 10, '2022-05-01'),

       (1, 2, '2022-03-01'),
       (1, 3, '2022-03-01'),
       (1, 4, '2022-03-15'),
       (1, 5, '2022-03-20'),
       (1, 6, '2022-04-01'),
       (1, 7, '2022-04-10'),
       (1, 8, '2022-04-15'),
       (1, 9, '2022-04-20'),
       (1, 10, '2022-04-25');


INSERT INTO enrollments (student_id, course_id, semester, enrolled_date, grade)
VALUES (1, 1, '2024-1', '2024-03-10', 17.5),
       (1, 2, '2024-1', '2024-03-10', 16.8),
       (2, 3, '2024-1', '2024-03-12', 15.9),
       (2, 4, '2024-1', '2024-03-12', 14.2),
       (3, 1, '2024-2', '2024-08-01', 18.3),
       (3, 2, '2024-2', '2024-08-01', 17.9),
       (4, 5, '2024-2', '2024-08-03', 15.7),
       (4, 7, '2024-2', '2024-08-03', 14.8),
       (5, 7, '2024-2', '2024-08-05', 16.1),
       (5, 8, '2024-2', '2024-08-05', 17.0),
       (6, 9, '2024-2', '2024-08-10', 18.4),
       (6, 10, '2024-2', '2024-08-10', 19.1),
       (7, 7, '2024-2', '2024-08-15', 15.3),
       (7, 8, '2024-2', '2024-08-15', 16.9),
       (8, 6, '2024-2', '2024-08-18', 18.7),
       (8, 9, '2024-2', '2024-08-18', 17.5),
       (9, 1, '2024-2', '2024-08-20', 14.9),
       (9, 3, '2024-2', '2024-08-20', 13.5),
       (10, 10, '2024-2', '2024-08-22', 15.8);

INSERT INTO exams (course_id, title, exam_date)
VALUES (1, 'Midterm Exam', '2024-04-20'),
       (1, 'Final Exam', '2024-06-25'),
       (2, 'Database Midterm', '2024-04-18'),
       (2, 'Database Final', '2024-06-22'),
       (3, 'Circuit Analysis Test 1', '2024-04-10'),
       (3, 'Circuit Analysis Final', '2024-06-20'),
       (7, 'Microeconomics Midterm', '2024-04-15'),
       (7, 'Microeconomics Final', '2024-06-18'),
       (9, 'Cognitive Psychology Midterm', '2024-04-25'),
       (9, 'Cognitive Psychology Final', '2024-06-30');

INSERT INTO exam_results (exam_id, student_id, score)
VALUES (1, 1, 16.5),
       (2, 1, 18.0),
       (1, 3, 17.8),
       (2, 3, 19.0),
       (3, 1, 15.9),
       (4, 1, 17.5),
       (3, 3, 18.3),
       (4, 3, 19.2),
       (5, 2, 14.8),
       (6, 2, 15.5),
       (5, 9, 13.9),
       (6, 9, 14.7),
       (7, 4, 15.3),
       (8, 4, 16.0),
       (7, 5, 17.2),
       (8, 5, 18.1),
       (9, 6, 18.5),
       (10, 6, 19.3),
       (9, 8, 17.6),
       (10, 8, 18.9);


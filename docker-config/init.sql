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


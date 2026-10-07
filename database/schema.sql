-- ============================================
-- Student Course Management System
-- Database Schema
-- PostgreSQL
-- ============================================


-- ============================================
-- 1. STUDENTS
-- ============================================

CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);


-- ============================================
-- 2. COURSES
-- ============================================

CREATE TABLE IF NOT EXISTS courses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    credit INTEGER NOT NULL CHECK (credit > 0)
);


-- ============================================
-- 3. ENROLLMENTS
-- Connects students with courses
-- ============================================

CREATE TABLE IF NOT EXISTS enrollments (
    id SERIAL PRIMARY KEY,

    student_id INTEGER NOT NULL,
    course_id INTEGER NOT NULL,

    CONSTRAINT fk_enrollment_student
        FOREIGN KEY (student_id)
        REFERENCES students(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_enrollment_course
        FOREIGN KEY (course_id)
        REFERENCES courses(id)
        ON DELETE CASCADE,

    CONSTRAINT unique_student_course
        UNIQUE (student_id, course_id)
);


-- ============================================
-- 4. GRADES
-- Stores grades for enrolled courses
-- ============================================

CREATE TABLE IF NOT EXISTS grades (
    id SERIAL PRIMARY KEY,

    enrollment_id INTEGER NOT NULL,
    grade NUMERIC(5,2) CHECK (grade >= 0 AND grade <= 100),

    CONSTRAINT fk_grade_enrollment
        FOREIGN KEY (enrollment_id)
        REFERENCES enrollments(id)
        ON DELETE CASCADE
);
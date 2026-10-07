# Student Course Management System

A console-based student course management system developed using Java, PostgreSQL, JDBC, and Maven.

## Features

- Add, list, find, update, and delete students
- Add, list, find, update, and delete courses
- Enroll students in courses
- List and delete enrollments
- Add, list, update, and delete grades
- View enrollment details using SQL JOIN operations
- Input validation for student, course, and grade information
- PostgreSQL database integration through JDBC

## Technologies

- Java
- PostgreSQL
- JDBC
- Maven
- SQL
- Git
- GitHub
- IntelliJ IDEA

## Database Structure

The system uses a PostgreSQL database with the following tables:

- `students` - stores student information
- `courses` - stores course information
- `enrollments` - manages student-course relationships
- `grades` - stores grades for enrollments

The database schema is available in:

```text
database/schema.sql
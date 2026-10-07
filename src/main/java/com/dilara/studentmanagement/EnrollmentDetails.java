package com.dilara.studentmanagement;

public class EnrollmentDetails {

    private int enrollmentId;
    private String studentName;
    private String studentSurname;
    private String courseName;
    private double grade;

    public EnrollmentDetails(
            int enrollmentId,
            String studentName,
            String studentSurname,
            String courseName,
            double grade) {

        this.enrollmentId = enrollmentId;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.courseName = courseName;
        this.grade = grade;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student: " + studentName + " " + studentSurname +
                " | Course: " + courseName +
                " | Grade: " + grade;
    }
}
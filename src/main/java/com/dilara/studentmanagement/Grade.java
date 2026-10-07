package com.dilara.studentmanagement;

public class Grade {

    private int id;
    private int enrollmentId;
    private double grade;

    public Grade(int id, int enrollmentId, double grade) {
        this.id = id;
        this.enrollmentId = enrollmentId;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", enrollmentId=" + enrollmentId +
                ", grade=" + grade +
                '}';
    }
}
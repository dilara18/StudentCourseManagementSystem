package com.dilara.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    // CREATE - Öğrenciyi derse kaydet
    public void enrollStudent(int studentId, int courseId) {

        String sql = """
                INSERT INTO enrollments (student_id, course_id)
                VALUES (?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            statement.setInt(2, courseId);

            statement.executeUpdate();

            System.out.println("Student enrolled successfully.");

        } catch (SQLException e) {
            System.out.println("Error enrolling student.");
            e.printStackTrace();
        }
    }


    // READ - Bütün kayıtları getir
    public List<Enrollment> getAllEnrollments() {

        List<Enrollment> enrollments = new ArrayList<>();

        String sql = "SELECT * FROM enrollments ORDER BY id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Enrollment enrollment = new Enrollment(
                        resultSet.getInt("id"),
                        resultSet.getInt("student_id"),
                        resultSet.getInt("course_id")
                );

                enrollments.add(enrollment);
            }

        } catch (SQLException e) {
            System.out.println("Error getting enrollments.");
            e.printStackTrace();
        }

        return enrollments;
    }

    // READ - Öğrenci, ders ve not bilgilerini birlikte getir
    public List<EnrollmentDetails> getEnrollmentDetails() {

        List<EnrollmentDetails> details = new ArrayList<>();

        String sql = """
            SELECT
                e.id AS enrollment_id,
                s.name AS student_name,
                s.surname AS student_surname,
                c.name AS course_name,
                g.grade AS grade
            FROM enrollments e
            JOIN students s
                ON e.student_id = s.id
            JOIN courses c
                ON e.course_id = c.id
            LEFT JOIN grades g
                ON e.id = g.enrollment_id
            ORDER BY e.id
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                double grade = resultSet.getDouble("grade");

                EnrollmentDetails enrollmentDetails =
                        new EnrollmentDetails(
                                resultSet.getInt("enrollment_id"),
                                resultSet.getString("student_name"),
                                resultSet.getString("student_surname"),
                                resultSet.getString("course_name"),
                                grade
                        );

                details.add(enrollmentDetails);
            }

        } catch (SQLException e) {
            System.out.println("Error getting enrollment details.");
            e.printStackTrace();
        }

        return details;
    }
    // DELETE - Öğrencinin dersten kaydını sil
    public void deleteEnrollment(int id) {

        String sql = "DELETE FROM enrollments WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Enrollment deleted successfully.");
            } else {
                System.out.println("Enrollment not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting enrollment.");
            e.printStackTrace();
        }
    }
}
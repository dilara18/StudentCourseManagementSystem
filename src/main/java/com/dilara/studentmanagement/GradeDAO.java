package com.dilara.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {

    // CREATE - Not ekleme
    public void addGrade(Grade grade) {

        String sql = """
                INSERT INTO grades (enrollment_id, grade)
                VALUES (?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, grade.getEnrollmentId());
            statement.setDouble(2, grade.getGrade());

            statement.executeUpdate();

            System.out.println("Grade added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding grade.");
            e.printStackTrace();
        }
    }


    // READ - Bütün notları getir
    public List<Grade> getAllGrades() {

        List<Grade> grades = new ArrayList<>();

        String sql = "SELECT * FROM grades ORDER BY id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Grade grade = new Grade(
                        resultSet.getInt("id"),
                        resultSet.getInt("enrollment_id"),
                        resultSet.getDouble("grade")
                );

                grades.add(grade);
            }

        } catch (SQLException e) {
            System.out.println("Error getting grades.");
            e.printStackTrace();
        }

        return grades;
    }


    // UPDATE - Not güncelleme
    public void updateGrade(Grade grade) {

        String sql = """
                UPDATE grades
                SET grade = ?
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, grade.getGrade());
            statement.setInt(2, grade.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Grade updated successfully.");
            } else {
                System.out.println("Grade not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating grade.");
            e.printStackTrace();
        }
    }


    // DELETE - Not silme
    public void deleteGrade(int id) {

        String sql = "DELETE FROM grades WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Grade deleted successfully.");
            } else {
                System.out.println("Grade not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting grade.");
            e.printStackTrace();
        }
    }
}
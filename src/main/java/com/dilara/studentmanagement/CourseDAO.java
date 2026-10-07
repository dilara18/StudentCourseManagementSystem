package com.dilara.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // CREATE - Yeni ders ekleme
    public void addCourse(Course course) {

        String sql = """
                INSERT INTO courses (name, credit)
                VALUES (?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, course.getName());
            statement.setInt(2, course.getCredit());

            statement.executeUpdate();

            System.out.println("Course added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding course.");
            e.printStackTrace();
        }
    }

    // READ - Bütün dersleri getirme
    public List<Course> getAllCourses() {

        List<Course> courses = new ArrayList<>();

        String sql = "SELECT * FROM courses ORDER BY id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Course course = new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("credit")
                );

                courses.add(course);
            }

        } catch (SQLException e) {
            System.out.println("Error getting courses.");
            e.printStackTrace();
        }

        return courses;
    }

    // READ - ID ile ders bulma
    public Course findCourseById(int id) {

        String sql = "SELECT * FROM courses WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("credit")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error finding course.");
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE - Ders bilgilerini güncelleme
    public void updateCourse(Course course) {

        String sql = """
                UPDATE courses
                SET name = ?, credit = ?
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, course.getName());
            statement.setInt(2, course.getCredit());
            statement.setInt(3, course.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Course updated successfully.");
            } else {
                System.out.println("Course not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating course.");
            e.printStackTrace();
        }
    }

    // DELETE - Ders silme
    public void deleteCourse(int id) {

        String sql = "DELETE FROM courses WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Course deleted successfully.");
            } else {
                System.out.println("Course not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting course.");
            e.printStackTrace();
        }
    }
}
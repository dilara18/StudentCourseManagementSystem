package com.dilara.studentmanagement;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        GradeDAO gradeDAO = new GradeDAO();

        boolean running = true;

        while (running) {

            System.out.println("\n================================");
            System.out.println("   STUDENT COURSE MANAGEMENT");
            System.out.println("================================");

            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Find Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");

            System.out.println("-------------------------------");

            System.out.println("6. Add Course");
            System.out.println("7. List Courses");
            System.out.println("8. Find Course");
            System.out.println("9. Update Course");
            System.out.println("10. Delete Course");

            System.out.println("-------------------------------");

            System.out.println("11. Enroll Student in Course");
            System.out.println("12. List Enrollments");
            System.out.println("13. Drop Course");
            System.out.println("14. View Enrollment Details");

            System.out.println("-------------------------------");

            System.out.println("15. Add Grade");
            System.out.println("16. List Grades");
            System.out.println("17. Update Grade");
            System.out.println("18. Delete Grade");

            System.out.println("-------------------------------");

            System.out.println("19. Exit");

            System.out.println("================================");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                // =================================
                // STUDENT OPERATIONS
                // =================================

                case 1:

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    if (name.trim().isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        break;
                    }

                    System.out.print("Surname: ");
                    String surname = scanner.nextLine();

                    if (surname.trim().isEmpty()) {
                        System.out.println("Surname cannot be empty.");
                        break;
                    }

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    if (email.trim().isEmpty()) {
                        System.out.println("Email cannot be empty.");
                        break;
                    }

                    if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                        System.out.println("Invalid email format.");
                        break;
                    }

                    Student student = new Student(
                            0,
                            name,
                            surname,
                            email
                    );

                    studentDAO.addStudent(student);

                    break;


                case 2:

                    List<Student> students =
                            studentDAO.getAllStudents();

                    System.out.println("\n--- STUDENTS ---");

                    if (students.isEmpty()) {

                        System.out.println("No students found.");

                    } else {

                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }

                    break;


                case 3:

                    System.out.print("Enter student ID: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();

                    Student foundStudent =
                            studentDAO.findStudentById(searchId);

                    if (foundStudent != null) {

                        System.out.println("\nStudent found:");
                        System.out.println(foundStudent);

                    } else {

                        System.out.println("Student not found.");
                    }

                    break;


                case 4:

                    System.out.print("Enter student ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    Student existingStudent =
                            studentDAO.findStudentById(updateId);

                    if (existingStudent == null) {

                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.print("New name: ");
                    String newName = scanner.nextLine();

                    System.out.print("New surname: ");
                    String newSurname = scanner.nextLine();

                    System.out.print("New email: ");
                    String newEmail = scanner.nextLine();

                    Student updatedStudent = new Student(
                            updateId,
                            newName,
                            newSurname,
                            newEmail
                    );

                    studentDAO.updateStudent(updatedStudent);

                    break;


                case 5:

                    System.out.print("Enter student ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();

                    studentDAO.deleteStudent(deleteId);

                    break;


                // =================================
                // COURSE OPERATIONS
                // =================================

                case 6:

                    System.out.print("Course name: ");
                    String courseName = scanner.nextLine();

                    if (courseName.trim().isEmpty()) {
                        System.out.println("Course name cannot be empty.");
                        break;
                    }

                    System.out.print("Credit: ");
                    int credit = scanner.nextInt();
                    scanner.nextLine();

                    if (credit <= 0) {
                        System.out.println("Credit must be greater than 0.");
                        break;
                    }

                    Course course = new Course(
                            0,
                            courseName,
                            credit
                    );

                    courseDAO.addCourse(course);

                    break;


                case 7:

                    List<Course> courses =
                            courseDAO.getAllCourses();

                    System.out.println("\n--- COURSES ---");

                    if (courses.isEmpty()) {

                        System.out.println("No courses found.");

                    } else {

                        for (Course c : courses) {
                            System.out.println(c);
                        }
                    }

                    break;


                case 8:

                    System.out.print("Enter course ID: ");
                    int courseSearchId = scanner.nextInt();
                    scanner.nextLine();

                    Course foundCourse =
                            courseDAO.findCourseById(courseSearchId);

                    if (foundCourse != null) {

                        System.out.println("\nCourse found:");
                        System.out.println(foundCourse);

                    } else {

                        System.out.println("Course not found.");
                    }

                    break;


                case 9:

                    System.out.print("Enter course ID to update: ");
                    int courseUpdateId = scanner.nextInt();
                    scanner.nextLine();

                    Course existingCourse =
                            courseDAO.findCourseById(courseUpdateId);

                    if (existingCourse == null) {

                        System.out.println("Course not found.");
                        break;
                    }

                    System.out.print("New course name: ");
                    String newCourseName = scanner.nextLine();

                    System.out.print("New credit: ");
                    int newCredit = scanner.nextInt();
                    scanner.nextLine();

                    Course updatedCourse = new Course(
                            courseUpdateId,
                            newCourseName,
                            newCredit
                    );

                    courseDAO.updateCourse(updatedCourse);

                    break;


                case 10:

                    System.out.print("Enter course ID to delete: ");
                    int courseDeleteId = scanner.nextInt();
                    scanner.nextLine();

                    courseDAO.deleteCourse(courseDeleteId);

                    break;


                // =================================
                // ENROLLMENT OPERATIONS
                // =================================

                case 11:

                    System.out.print("Enter student ID: ");
                    int enrollStudentId = scanner.nextInt();

                    System.out.print("Enter course ID: ");
                    int enrollCourseId = scanner.nextInt();
                    scanner.nextLine();

                    Student studentToEnroll =
                            studentDAO.findStudentById(enrollStudentId);

                    Course courseToEnroll =
                            courseDAO.findCourseById(enrollCourseId);

                    if (studentToEnroll == null) {

                        System.out.println("Student not found.");

                    } else if (courseToEnroll == null) {

                        System.out.println("Course not found.");

                    } else {

                        enrollmentDAO.enrollStudent(
                                enrollStudentId,
                                enrollCourseId
                        );
                    }

                    break;


                case 12:

                    List<Enrollment> enrollments =
                            enrollmentDAO.getAllEnrollments();

                    System.out.println("\n--- ENROLLMENTS ---");

                    if (enrollments.isEmpty()) {

                        System.out.println("No enrollments found.");

                    } else {

                        for (Enrollment enrollment : enrollments) {
                            System.out.println(enrollment);
                        }
                    }

                    break;


                case 13:

                    System.out.print("Enter enrollment ID to delete: ");
                    int deleteEnrollmentId = scanner.nextInt();
                    scanner.nextLine();

                    enrollmentDAO.deleteEnrollment(
                            deleteEnrollmentId
                    );

                    break;


                // =================================
                // JOIN - ENROLLMENT DETAILS
                // =================================

                case 14:

                    List<EnrollmentDetails> details =
                            enrollmentDAO.getEnrollmentDetails();

                    System.out.println(
                            "\n--- ENROLLMENT DETAILS ---"
                    );

                    if (details.isEmpty()) {

                        System.out.println(
                                "No enrollment details found."
                        );

                    } else {

                        for (EnrollmentDetails detail : details) {
                            System.out.println(detail);
                        }
                    }

                    break;


                // =================================
                // GRADE OPERATIONS
                // =================================

                case 15:

                    System.out.print("Enter enrollment ID: ");
                    int gradeEnrollmentId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter grade (0-100): ");
                    String gradeInput = scanner.nextLine();

                    double gradeValue;

                    try {

                        gradeValue = Double.parseDouble(
                                gradeInput.replace(",", ".")
                        );

                    } catch (NumberFormatException e) {

                        System.out.println("Invalid grade. Please enter a number.");
                        break;
                    }

                    if (gradeValue < 0 || gradeValue > 100) {

                        System.out.println("Grade must be between 0 and 100.");
                        break;
                    }

                    Grade grade = new Grade(
                            0,
                            gradeEnrollmentId,
                            gradeValue
                    );

                    gradeDAO.addGrade(grade);

                    break;

                case 16:

                    List<Grade> grades =
                            gradeDAO.getAllGrades();

                    System.out.println("\n--- GRADES ---");

                    if (grades.isEmpty()) {

                        System.out.println("No grades found.");

                    } else {

                        for (Grade g : grades) {
                            System.out.println(g);
                        }
                    }

                    break;


                case 17:

                    System.out.print("Enter grade ID to update: ");
                    int gradeId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new grade (0-100): ");
                    String newGradeInput = scanner.nextLine();

                    double newGrade;

                    try {

                        newGrade = Double.parseDouble(
                                newGradeInput.replace(",", ".")
                        );

                    } catch (NumberFormatException e) {

                        System.out.println("Invalid grade. Please enter a number.");
                        break;
                    }

                    if (newGrade < 0 || newGrade > 100) {

                        System.out.println("Grade must be between 0 and 100.");
                        break;
                    }

                    Grade updatedGrade = new Grade(
                            gradeId,
                            0,
                            newGrade
                    );

                    gradeDAO.updateGrade(updatedGrade);

                    break;


                case 18:

                    System.out.print("Enter grade ID to delete: ");
                    int deleteGradeId = scanner.nextInt();
                    scanner.nextLine();

                    gradeDAO.deleteGrade(deleteGradeId);

                    break;


                // =================================
                // EXIT
                // =================================

                case 19:

                    running = false;

                    System.out.println("Goodbye!");

                    break;


                default:

                    System.out.println("Invalid option.");
            }
        }

        // Scanner'ı burada kapatmıyoruz.
        // Program zaten sona erdiğinde kapanacaktır.
    }
}
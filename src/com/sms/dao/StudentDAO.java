package com.sms.dao;

import com.sms.model.Student;
import com.sms.util.Constants;
import com.sms.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDAO (Data Access Object) Class - Enhanced
 * Handles all database CRUD operations for Student entity
 * Follows DAO design pattern with improved error handling and logging
 */
public class StudentDAO {

    // SQL Queries from Constants class

    /**
     * Add a new student to database
     * 
     * @param student Student object to add
     * @return true if successful, false otherwise
     */
    public boolean addStudent(Student student) {
        // Check if roll number already exists
        if (isRollNumberExists(student.getRollNumber())) {
            System.err.println("✗ Roll Number already exists: " + student.getRollNumber());
            return false;
        }

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(Constants.SQL.INSERT_STUDENT)) {

            // Set parameters
            pstmt.setString(1, student.getRollNumber());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getPhone());
            pstmt.setString(5, student.getCourse());
            pstmt.setDouble(6, student.getMarks());

            // Execute update
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✓ Student added successfully: " + student.getRollNumber());
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Error adding student: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get all students from database
     * 
     * @return List of all students
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(Constants.SQL.SELECT_ALL_STUDENTS)) {

            while (rs.next()) {
                Student student = extractStudentFromResultSet(rs);
                students.add(student);
            }

            System.out.println("✓ Retrieved " + students.size() + " students");

        } catch (SQLException e) {
            System.err.println("✗ Error retrieving students: " + e.getMessage());
            e.printStackTrace();
        }

        return students;
    }

    /**
     * Get student by roll number
     * 
     * @param rollNumber Roll number to search
     * @return Student object if found, null otherwise
     */
    public Student getStudentByRollNumber(String rollNumber) {
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(Constants.SQL.SELECT_STUDENT_BY_ROLL)) {

            pstmt.setString(1, rollNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Student student = extractStudentFromResultSet(rs);
                System.out.println("✓ Student found: " + rollNumber);
                return student;
            } else {
                System.out.println("✗ Student not found: " + rollNumber);
            }

        } catch (SQLException e) {
            System.err.println("✗ Error searching student: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Search students by name (partial match)
     * 
     * @param name Name to search
     * @return List of matching students
     */
    public List<Student> searchStudentsByName(String name) {
        List<Student> students = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(Constants.SQL.SELECT_STUDENT_BY_NAME)) {

            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = extractStudentFromResultSet(rs);
                students.add(student);
            }

            System.out.println("✓ Found " + students.size() + " students matching: " + name);

        } catch (SQLException e) {
            System.err.println("✗ Error searching students: " + e.getMessage());
            e.printStackTrace();
        }

        return students;
    }

    /**
     * Update student details
     * 
     * @param student Student object with updated data
     * @return true if successful, false otherwise
     */
    public boolean updateStudent(Student student) {
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(Constants.SQL.UPDATE_STUDENT)) {

            // Set parameters (note: roll_number is in WHERE clause)
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getPhone());
            pstmt.setString(4, student.getCourse());
            pstmt.setDouble(5, student.getMarks());
            pstmt.setString(6, student.getRollNumber());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✓ Student updated successfully: " + student.getRollNumber());
                return true;
            } else {
                System.out.println("✗ Student not found for update: " + student.getRollNumber());
            }

        } catch (SQLException e) {
            System.err.println("✗ Error updating student: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Delete student by roll number
     * 
     * @param rollNumber Roll number of student to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteStudent(String rollNumber) {
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(Constants.SQL.DELETE_STUDENT)) {

            pstmt.setString(1, rollNumber);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✓ Student deleted successfully: " + rollNumber);
                return true;
            } else {
                System.out.println("✗ Student not found for deletion: " + rollNumber);
            }

        } catch (SQLException e) {
            System.err.println("✗ Error deleting student: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Check if roll number already exists
     * 
     * @param rollNumber Roll number to check
     * @return true if exists, false otherwise
     */
    public boolean isRollNumberExists(String rollNumber) {
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(Constants.SQL.CHECK_ROLL_EXISTS)) {

            pstmt.setString(1, rollNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("✗ Error checking roll number: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Helper method to extract Student object from ResultSet
     * 
     * @param rs ResultSet from query
     * @return Student object
     * @throws SQLException if error reading ResultSet
     */
    private Student extractStudentFromResultSet(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setRollNumber(rs.getString("roll_number"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        student.setPhone(rs.getString("phone"));
        student.setCourse(rs.getString("course"));
        student.setMarks(rs.getDouble("marks"));
        student.setCreatedAt(rs.getTimestamp("created_at"));
        student.setUpdatedAt(rs.getTimestamp("updated_at"));
        return student;
    }
}

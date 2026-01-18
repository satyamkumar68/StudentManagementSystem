package com.sms;

import com.sms.ui.StudentFrame;
import com.sms.util.DatabaseConnection;

import javax.swing.*;

/**
 * Main Class - Entry point for Student Management System
 * Tests database connection and launches the UI
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  STUDENT MANAGEMENT SYSTEM");
        System.out.println("  Developed using Java, Swing, MySQL, JDBC");
        System.out.println("===========================================\n");

        // Test database connection
        System.out.println("Testing database connection...");

        if (DatabaseConnection.testConnection()) {
            System.out.println("✓ Database connection successful!\n");

            // Launch UI on Event Dispatch Thread (Best Practice for Swing)
            SwingUtilities.invokeLater(() -> {
                try {
                    new StudentFrame();
                    System.out.println("✓ Application started successfully!");
                } catch (Exception e) {
                    System.err.println("✗ Error starting application: " + e.getMessage());
                    e.printStackTrace();

                    JOptionPane.showMessageDialog(null,
                            "Error starting application:\n" + e.getMessage(),
                            "Application Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });

        } else {
            System.err.println("✗ Database connection failed!");
            System.err.println("\nPlease check:");
            System.err.println("1. MySQL server is running");
            System.err.println("2. Database 'student_management_db' exists");
            System.err.println("3. Username and password in DatabaseConnection.java are correct");
            System.err.println("4. MySQL JDBC driver is in classpath");

            JOptionPane.showMessageDialog(null,
                    "Database connection failed!\n\n" +
                            "Please ensure:\n" +
                            "1. MySQL server is running\n" +
                            "2. Database exists (run setup.sql)\n" +
                            "3. Credentials are correct\n" +
                            "4. JDBC driver is installed",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }
    }
}

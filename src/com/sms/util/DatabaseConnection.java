package com.sms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection Utility Class
 * Manages database connections using Singleton pattern
 * Provides reusable connection method for DAO classes
 */
public class DatabaseConnection {

    // Database credentials (In production, use properties file or environment
    // variables)
    private static final String URL = "jdbc:mysql://localhost:3306/student_management_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "965023"; // Password set in MySQL Workbench

    // JDBC Driver class name
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Static connection instance (Singleton pattern)
    private static Connection connection = null;

    // Private constructor to prevent instantiation
    private DatabaseConnection() {
    }

    /**
     * Get database connection
     * 
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC Driver
            Class.forName(DRIVER);

            // Create connection if it doesn't exist or is closed
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("✓ Database connected successfully!");
            }

            return connection;

        } catch (ClassNotFoundException e) {
            System.err.println("✗ MySQL JDBC Driver not found!");
            System.err.println("Please add mysql-connector-java.jar to your project classpath");
            throw new SQLException("Driver not found", e);
        } catch (SQLException e) {
            System.err.println("✗ Database connection failed!");
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Close database connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("✗ Error closing connection: " + e.getMessage());
        }
    }

    /**
     * Test database connection
     * 
     * @return true if connection successful, false otherwise
     */
    public static boolean testConnection() {
        try {
            Connection conn = getConnection();
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}

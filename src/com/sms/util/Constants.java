package com.sms.util;

import java.awt.Color;

/**
 * Constants Class - Centralized configuration and constants
 * Contains all magic numbers, strings, colors, and SQL queries
 */
public class Constants {

    // ==================== DATABASE CONSTANTS ====================

    // SQL Queries
    public static final class SQL {
        public static final String INSERT_STUDENT = "INSERT INTO students (roll_number, name, email, phone, course, marks) VALUES (?, ?, ?, ?, ?, ?)";

        public static final String SELECT_ALL_STUDENTS = "SELECT * FROM students ORDER BY roll_number";

        public static final String SELECT_STUDENT_BY_ROLL = "SELECT * FROM students WHERE roll_number = ?";

        public static final String SELECT_STUDENT_BY_NAME = "SELECT * FROM students WHERE name LIKE ?";

        public static final String UPDATE_STUDENT = "UPDATE students SET name = ?, email = ?, phone = ?, course = ?, marks = ? WHERE roll_number = ?";

        public static final String DELETE_STUDENT = "DELETE FROM students WHERE roll_number = ?";

        public static final String CHECK_ROLL_EXISTS = "SELECT COUNT(*) FROM students WHERE roll_number = ?";
    }

    // ==================== VALIDATION CONSTANTS ====================

    public static final class Validation {
        public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
        public static final String PHONE_PATTERN = "\\d{10}";
        public static final double MIN_MARKS = 0.0;
        public static final double MAX_MARKS = 100.0;
        public static final int PHONE_LENGTH = 10;
    }

    // ==================== UI CONSTANTS ====================

    // Modern Color Palette
    public static final class Colors {
        // Primary Colors
        public static final Color PRIMARY = new Color(99, 102, 241); // Indigo
        public static final Color PRIMARY_DARK = new Color(79, 70, 229); // Darker Indigo
        public static final Color PRIMARY_LIGHT = new Color(165, 180, 252); // Light Indigo

        // Semantic Colors
        public static final Color SUCCESS = new Color(16, 185, 129); // Emerald
        public static final Color SUCCESS_DARK = new Color(5, 150, 105); // Dark Emerald
        public static final Color DANGER = new Color(239, 68, 68); // Red
        public static final Color DANGER_DARK = new Color(220, 38, 38); // Dark Red
        public static final Color WARNING = new Color(245, 158, 11); // Amber
        public static final Color WARNING_DARK = new Color(217, 119, 6); // Dark Amber
        public static final Color INFO = new Color(59, 130, 246); // Blue
        public static final Color INFO_DARK = new Color(37, 99, 235); // Dark Blue

        // Neutral Colors
        public static final Color BACKGROUND = new Color(248, 250, 252); // Slate 50
        public static final Color SURFACE = new Color(255, 255, 255); // White
        public static final Color BORDER = new Color(226, 232, 240); // Slate 200
        public static final Color TEXT_PRIMARY = new Color(15, 23, 42); // Slate 900
        public static final Color TEXT_SECONDARY = new Color(100, 116, 139);// Slate 500
        public static final Color DISABLED = new Color(148, 163, 184); // Slate 400

        // Table Colors
        public static final Color TABLE_HEADER = PRIMARY;
        public static final Color TABLE_HEADER_TEXT = Color.WHITE;
        public static final Color TABLE_ROW_EVEN = new Color(248, 250, 252);
        public static final Color TABLE_ROW_ODD = Color.WHITE;
        public static final Color TABLE_SELECTION = PRIMARY_LIGHT;
        public static final Color TABLE_SELECTION_TEXT = TEXT_PRIMARY;
        public static final Color TABLE_GRID = BORDER;
    }

    // UI Dimensions
    public static final class Dimensions {
        public static final int WINDOW_WIDTH = 1200;
        public static final int WINDOW_HEIGHT = 700;
        public static final int HEADER_HEIGHT = 70;
        public static final int FOOTER_HEIGHT = 60;
        public static final int FORM_PANEL_WIDTH = 420;
        public static final int BUTTON_HEIGHT = 45;
        public static final int TEXT_FIELD_HEIGHT = 35;
        public static final int BORDER_RADIUS = 8;
    }

    // Fonts
    public static final class Fonts {
        public static final String FONT_FAMILY = "Segoe UI";
        public static final int TITLE_SIZE = 32;
        public static final int HEADING_SIZE = 18;
        public static final int LABEL_SIZE = 14;
        public static final int TEXT_SIZE = 14;
        public static final int TABLE_HEADER_SIZE = 14;
        public static final int TABLE_CELL_SIZE = 13;
    }

    // ==================== MESSAGE CONSTANTS ====================

    public static final class Messages {
        // Success Messages
        public static final String STUDENT_ADDED = "Student added successfully!";
        public static final String STUDENT_UPDATED = "Student updated successfully!";
        public static final String STUDENT_DELETED = "Student deleted successfully!";
        public static final String DB_CONNECTED = "✓ Database connected successfully!";

        // Error Messages
        public static final String VALIDATION_ALL_REQUIRED = "All fields are required!";
        public static final String VALIDATION_INVALID_EMAIL = "Invalid email format!";
        public static final String VALIDATION_INVALID_PHONE = "Phone number must be 10 digits!";
        public static final String VALIDATION_INVALID_MARKS = "Marks must be between 0 and 100!";
        public static final String VALIDATION_INVALID_MARKS_FORMAT = "Marks must be a valid number!";

        public static final String ERROR_ADD_FAILED = "Failed to add student. Roll number may already exist.";
        public static final String ERROR_UPDATE_FAILED = "Failed to update student.";
        public static final String ERROR_DELETE_FAILED = "Failed to delete student.";
        public static final String ERROR_NO_SELECTION = "Please select a student from the table.";
        public static final String ERROR_EMPTY_SEARCH = "Please enter roll number or name to search.";
        public static final String ERROR_NO_RESULTS = "No students found matching: ";

        public static final String ERROR_DB_CONNECTION = "Database connection failed!";
        public static final String ERROR_DRIVER_NOT_FOUND = "✗ MySQL JDBC Driver not found!";

        // Confirmation Messages
        public static final String CONFIRM_DELETE = "Are you sure you want to delete student: ";

        // Info Messages
        public static final String INFO_STUDENT_FOUND = "✓ Student found: ";
        public static final String INFO_STUDENT_NOT_FOUND = "✗ Student not found: ";
        public static final String INFO_STUDENTS_RETRIEVED = "✓ Retrieved %d students";
        public static final String INFO_STUDENTS_MATCHING = "✓ Found %d students matching: ";
    }

    // ==================== TABLE CONSTANTS ====================

    public static final class Table {
        public static final String[] COLUMN_NAMES = {
                "Roll Number", "Name", "Email", "Phone", "Course", "Marks"
        };
        public static final int ROW_HEIGHT = 32;
        public static final int HEADER_HEIGHT = 35;
    }

    // ==================== KEYBOARD SHORTCUTS ====================

    public static final class KeyboardShortcuts {
        public static final String ADD = "ctrl S";
        public static final String UPDATE = "ctrl U";
        public static final String DELETE = "ctrl D";
        public static final String CLEAR = "ctrl L";
        public static final String SEARCH = "ctrl F";
        public static final String REFRESH = "F5";
    }

    // ==================== ICONS (Unicode) ====================

    public static final class Icons {
        public static final String ADD = "➕ ";
        public static final String UPDATE = "✏️ ";
        public static final String DELETE = "🗑️ ";
        public static final String CLEAR = "🔄 ";
        public static final String SEARCH = "🔍 ";
        public static final String REFRESH = "↻ ";
        public static final String SAVE = "💾 ";
        public static final String STUDENT = "👨‍🎓 ";
        public static final String SUCCESS = "✓ ";
        public static final String ERROR = "✗ ";
        public static final String WARNING = "⚠ ";
        public static final String INFO = "ℹ ";
    }

    // Private constructor to prevent instantiation
    private Constants() {
        throw new AssertionError("Cannot instantiate Constants class");
    }
}

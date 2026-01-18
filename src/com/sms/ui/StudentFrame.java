package com.sms.ui;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * StudentFrame - Main UI Class using Swing
 * Provides complete GUI for Student Management System
 * Implements all CRUD operations with user-friendly interface
 */
public class StudentFrame extends JFrame {

    // DAO instance
    private StudentDAO studentDAO;

    // UI Components - Input Fields
    private JTextField txtRollNumber;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtCourse;
    private JTextField txtMarks;
    private JTextField txtSearch;

    // UI Components - Buttons
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnRefresh;

    // UI Components - Table
    private JTable studentTable;
    private DefaultTableModel tableModel;

    // Table column names
    private final String[] columnNames = { "Roll Number", "Name", "Email", "Phone", "Course", "Marks" };

    /**
     * Constructor - Initialize UI
     */
    public StudentFrame() {
        studentDAO = new StudentDAO();
        initializeUI();
        loadStudentData();
    }

    /**
     * Initialize all UI components
     */
    private void initializeUI() {
        // Frame settings
        setTitle("Student Management System");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout(10, 10));

        // Set Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create panels
        JPanel topPanel = createTopPanel();
        JPanel centerPanel = createCenterPanel();
        JPanel bottomPanel = createBottomPanel();

        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Create top panel with title
     */
    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(41, 128, 185));
        panel.setPreferredSize(new Dimension(0, 60));

        JLabel lblTitle = new JLabel("STUDENT MANAGEMENT SYSTEM");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        panel.add(lblTitle);

        return panel;
    }

    /**
     * Create center panel with input form and table
     */
    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Left panel - Input form
        JPanel formPanel = createFormPanel();

        // Right panel - Table
        JPanel tablePanel = createTablePanel();

        panel.add(formPanel, BorderLayout.WEST);
        panel.add(tablePanel, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Create form panel with input fields
     */
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
                "Student Details",
                0,
                0,
                new Font("Arial", Font.BOLD, 16),
                new Color(41, 128, 185)));
        panel.setPreferredSize(new Dimension(400, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Initialize text fields
        txtRollNumber = new JTextField(20);
        txtName = new JTextField(20);
        txtEmail = new JTextField(20);
        txtPhone = new JTextField(20);
        txtCourse = new JTextField(20);
        txtMarks = new JTextField(20);

        // Add components to panel
        addFormField(panel, gbc, "Roll Number:", txtRollNumber, 0);
        addFormField(panel, gbc, "Name:", txtName, 1);
        addFormField(panel, gbc, "Email:", txtEmail, 2);
        addFormField(panel, gbc, "Phone:", txtPhone, 3);
        addFormField(panel, gbc, "Course:", txtCourse, 4);
        addFormField(panel, gbc, "Marks:", txtMarks, 5);

        // Button panel
        JPanel buttonPanel = createButtonPanel();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(buttonPanel, gbc);

        return panel;
    }

    /**
     * Helper method to add form fields
     */
    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField textField, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(textField, gbc);
    }

    /**
     * Create button panel
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        btnAdd = createStyledButton("Add Student", new Color(46, 204, 113));
        btnUpdate = createStyledButton("Update Student", new Color(52, 152, 219));
        btnDelete = createStyledButton("Delete Student", new Color(231, 76, 60));
        btnClear = createStyledButton("Clear Fields", new Color(149, 165, 166));

        // Add action listeners
        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearFields());

        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnClear);

        return panel;
    }

    /**
     * Create styled button
     */
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(0, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    /**
     * Create table panel
     */
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
                "Student Records",
                0,
                0,
                new Font("Arial", Font.BOLD, 16),
                new Color(41, 128, 185)));

        // Create table model
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };

        // Create table
        studentTable = new JTable(tableModel);
        studentTable.setFont(new Font("Arial", Font.PLAIN, 13));
        studentTable.setRowHeight(30);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        studentTable.getTableHeader().setBackground(new Color(41, 128, 185));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.setSelectionBackground(new Color(52, 152, 219));
        studentTable.setSelectionForeground(Color.WHITE);
        studentTable.setGridColor(new Color(189, 195, 199));

        // Add selection listener
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedStudent();
            }
        });

        JScrollPane scrollPane = new JScrollPane(studentTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Create bottom panel with search functionality
     */
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(new Color(236, 240, 241));
        panel.setPreferredSize(new Dimension(0, 60));

        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setFont(new Font("Arial", Font.BOLD, 14));

        txtSearch = new JTextField(25);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 14));
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        btnSearch = createStyledButton("Search", new Color(155, 89, 182));
        btnRefresh = createStyledButton("Refresh All", new Color(52, 73, 94));

        btnSearch.addActionListener(e -> searchStudent());
        btnRefresh.addActionListener(e -> loadStudentData());

        panel.add(lblSearch);
        panel.add(txtSearch);
        panel.add(btnSearch);
        panel.add(btnRefresh);

        return panel;
    }

    /**
     * Load all students into table
     */
    private void loadStudentData() {
        tableModel.setRowCount(0); // Clear existing data

        List<Student> students = studentDAO.getAllStudents();

        for (Student student : students) {
            Object[] row = {
                    student.getRollNumber(),
                    student.getName(),
                    student.getEmail(),
                    student.getPhone(),
                    student.getCourse(),
                    student.getMarks()
            };
            tableModel.addRow(row);
        }
    }

    /**
     * Load selected student from table to form
     */
    private void loadSelectedStudent() {
        int selectedRow = studentTable.getSelectedRow();

        if (selectedRow >= 0) {
            txtRollNumber.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtName.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtEmail.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtPhone.setText(tableModel.getValueAt(selectedRow, 3).toString());
            txtCourse.setText(tableModel.getValueAt(selectedRow, 4).toString());
            txtMarks.setText(tableModel.getValueAt(selectedRow, 5).toString());

            txtRollNumber.setEditable(false); // Prevent roll number change
        }
    }

    /**
     * Add new student
     */
    private void addStudent() {
        // Validate input
        if (!validateInput()) {
            return;
        }

        try {
            // Create student object
            Student student = new Student(
                    txtRollNumber.getText().trim(),
                    txtName.getText().trim(),
                    txtEmail.getText().trim(),
                    txtPhone.getText().trim(),
                    txtCourse.getText().trim(),
                    Double.parseDouble(txtMarks.getText().trim()));

            // Add to database
            if (studentDAO.addStudent(student)) {
                JOptionPane.showMessageDialog(this,
                        "Student added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadStudentData();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to add student. Roll number may already exist.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid marks format. Please enter a valid number.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Update existing student
     */
    private void updateStudent() {
        if (txtRollNumber.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a student from the table to update.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validateInput()) {
            return;
        }

        try {
            Student student = new Student(
                    txtRollNumber.getText().trim(),
                    txtName.getText().trim(),
                    txtEmail.getText().trim(),
                    txtPhone.getText().trim(),
                    txtCourse.getText().trim(),
                    Double.parseDouble(txtMarks.getText().trim()));

            if (studentDAO.updateStudent(student)) {
                JOptionPane.showMessageDialog(this,
                        "Student updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadStudentData();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to update student.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid marks format. Please enter a valid number.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Delete student
     */
    private void deleteStudent() {
        String rollNumber = txtRollNumber.getText().trim();

        if (rollNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a student from the table to delete.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete student: " + rollNumber + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            if (studentDAO.deleteStudent(rollNumber)) {
                JOptionPane.showMessageDialog(this,
                        "Student deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadStudentData();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to delete student.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Search student by roll number or name
     */
    private void searchStudent() {
        String searchText = txtSearch.getText().trim();

        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter roll number or name to search.",
                    "Empty Search",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.setRowCount(0);

        // Try searching by roll number first
        Student student = studentDAO.getStudentByRollNumber(searchText);

        if (student != null) {
            Object[] row = {
                    student.getRollNumber(),
                    student.getName(),
                    student.getEmail(),
                    student.getPhone(),
                    student.getCourse(),
                    student.getMarks()
            };
            tableModel.addRow(row);
        } else {
            // Search by name
            List<Student> students = studentDAO.searchStudentsByName(searchText);

            if (students.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No students found matching: " + searchText,
                        "No Results",
                        JOptionPane.INFORMATION_MESSAGE);
                loadStudentData();
            } else {
                for (Student s : students) {
                    Object[] row = {
                            s.getRollNumber(),
                            s.getName(),
                            s.getEmail(),
                            s.getPhone(),
                            s.getCourse(),
                            s.getMarks()
                    };
                    tableModel.addRow(row);
                }
            }
        }

        txtSearch.setText("");
    }

    /**
     * Clear all input fields
     */
    private void clearFields() {
        txtRollNumber.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtCourse.setText("");
        txtMarks.setText("");
        txtRollNumber.setEditable(true);
        studentTable.clearSelection();
    }

    /**
     * Validate input fields
     */
    private boolean validateInput() {
        // Check empty fields
        if (txtRollNumber.getText().trim().isEmpty() ||
                txtName.getText().trim().isEmpty() ||
                txtEmail.getText().trim().isEmpty() ||
                txtPhone.getText().trim().isEmpty() ||
                txtCourse.getText().trim().isEmpty() ||
                txtMarks.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "All fields are required!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate email format
        String email = txtEmail.getText().trim();
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this,
                    "Invalid email format!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate phone number
        String phone = txtPhone.getText().trim();
        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this,
                    "Phone number must be 10 digits!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate marks
        try {
            double marks = Double.parseDouble(txtMarks.getText().trim());
            if (marks < 0 || marks > 100) {
                JOptionPane.showMessageDialog(this,
                        "Marks must be between 0 and 100!",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Marks must be a valid number!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}

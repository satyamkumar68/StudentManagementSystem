package com.sms.ui;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;
import com.sms.util.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
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

    // Table components
    private TableRowSorter<DefaultTableModel> tableSorter;

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
        setTitle(Constants.Icons.STUDENT + "Student Management System");
        setSize(Constants.Dimensions.WINDOW_WIDTH, Constants.Dimensions.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Constants.Colors.BACKGROUND);

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
        panel.setBackground(Constants.Colors.PRIMARY);
        panel.setPreferredSize(new Dimension(0, Constants.Dimensions.HEADER_HEIGHT));

        JLabel lblTitle = new JLabel(Constants.Icons.STUDENT + " STUDENT MANAGEMENT SYSTEM");
        lblTitle.setFont(new Font(Constants.Fonts.FONT_FAMILY, Font.BOLD, Constants.Fonts.TITLE_SIZE));
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
                BorderFactory.createLineBorder(Constants.Colors.PRIMARY, 2),
                Constants.Icons.STUDENT + " Student Details",
                0,
                0,
                new Font(Constants.Fonts.FONT_FAMILY, Font.BOLD, Constants.Fonts.HEADING_SIZE),
                Constants.Colors.PRIMARY));
        panel.setPreferredSize(new Dimension(Constants.Dimensions.FORM_PANEL_WIDTH, 0));
        panel.setBackground(Constants.Colors.SURFACE);

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

        btnAdd = createStyledButton(Constants.Icons.ADD + "Add Student", Constants.Colors.SUCCESS);
        btnUpdate = createStyledButton(Constants.Icons.UPDATE + "Update", Constants.Colors.INFO);
        btnDelete = createStyledButton(Constants.Icons.DELETE + "Delete", Constants.Colors.DANGER);
        btnClear = createStyledButton(Constants.Icons.CLEAR + "Clear", Constants.Colors.DISABLED);

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
        button.setFont(new Font(Constants.Fonts.FONT_FAMILY, Font.BOLD, Constants.Fonts.LABEL_SIZE));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(0, Constants.Dimensions.BUTTON_HEIGHT));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

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
                BorderFactory.createLineBorder(Constants.Colors.PRIMARY, 2),
                "📋 Student Records",
                0,
                0,
                new Font(Constants.Fonts.FONT_FAMILY, Font.BOLD, Constants.Fonts.HEADING_SIZE),
                Constants.Colors.PRIMARY));
        panel.setBackground(Constants.Colors.SURFACE);

        // Create table model
        tableModel = new DefaultTableModel(Constants.Table.COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };

        // Create table with sorting
        studentTable = new JTable(tableModel);
        studentTable.setFont(new Font(Constants.Fonts.FONT_FAMILY, Font.PLAIN, Constants.Fonts.TABLE_CELL_SIZE));
        studentTable.setRowHeight(Constants.Table.ROW_HEIGHT);
        studentTable.getTableHeader()
                .setFont(new Font(Constants.Fonts.FONT_FAMILY, Font.BOLD, Constants.Fonts.TABLE_HEADER_SIZE));
        studentTable.getTableHeader().setBackground(Constants.Colors.TABLE_HEADER);
        studentTable.getTableHeader().setForeground(Constants.Colors.TABLE_HEADER_TEXT);
        studentTable.getTableHeader().setPreferredSize(new Dimension(0, Constants.Table.HEADER_HEIGHT));
        studentTable.setSelectionBackground(Constants.Colors.TABLE_SELECTION);
        studentTable.setSelectionForeground(Constants.Colors.TABLE_SELECTION_TEXT);
        studentTable.setGridColor(Constants.Colors.TABLE_GRID);
        studentTable.setShowGrid(true);
        studentTable.setIntercellSpacing(new Dimension(1, 1));

        // Add table sorting
        tableSorter = new TableRowSorter<>(tableModel);
        studentTable.setRowSorter(tableSorter);

        // Alternating row colors
        studentTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Constants.Colors.TABLE_ROW_EVEN : Constants.Colors.TABLE_ROW_ODD);
                }
                return c;
            }
        });

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
        panel.setBackground(Constants.Colors.BACKGROUND);
        panel.setPreferredSize(new Dimension(0, Constants.Dimensions.FOOTER_HEIGHT));

        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setFont(new Font("Arial", Font.BOLD, 14));

        txtSearch = new JTextField(25);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 14));
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        btnSearch = createStyledButton(Constants.Icons.SEARCH + "Search", Constants.Colors.WARNING);
        btnRefresh = createStyledButton(Constants.Icons.REFRESH + "Refresh All", Constants.Colors.PRIMARY_DARK);

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
                        Constants.Messages.STUDENT_ADDED,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadStudentData();
            } else {
                JOptionPane.showMessageDialog(this,
                        Constants.Messages.ERROR_ADD_FAILED,
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
                    Constants.Messages.ERROR_NO_SELECTION,
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
                        Constants.Messages.STUDENT_UPDATED,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadStudentData();
            } else {
                JOptionPane.showMessageDialog(this,
                        Constants.Messages.ERROR_UPDATE_FAILED,
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
                    Constants.Messages.ERROR_NO_SELECTION,
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                Constants.Messages.CONFIRM_DELETE + rollNumber + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            if (studentDAO.deleteStudent(rollNumber)) {
                JOptionPane.showMessageDialog(this,
                        Constants.Messages.STUDENT_DELETED,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadStudentData();
            } else {
                JOptionPane.showMessageDialog(this,
                        Constants.Messages.ERROR_DELETE_FAILED,
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
                    Constants.Messages.ERROR_EMPTY_SEARCH,
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
                        Constants.Messages.ERROR_NO_RESULTS + searchText,
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
                    Constants.Messages.VALIDATION_ALL_REQUIRED,
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate email format
        String email = txtEmail.getText().trim();
        if (!email.matches(Constants.Validation.EMAIL_PATTERN)) {
            JOptionPane.showMessageDialog(this,
                    Constants.Messages.VALIDATION_INVALID_EMAIL,
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate phone number
        String phone = txtPhone.getText().trim();
        if (!phone.matches(Constants.Validation.PHONE_PATTERN)) {
            JOptionPane.showMessageDialog(this,
                    Constants.Messages.VALIDATION_INVALID_PHONE,
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate marks
        try {
            double marks = Double.parseDouble(txtMarks.getText().trim());
            if (marks < Constants.Validation.MIN_MARKS || marks > Constants.Validation.MAX_MARKS) {
                JOptionPane.showMessageDialog(this,
                        Constants.Messages.VALIDATION_INVALID_MARKS,
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    Constants.Messages.VALIDATION_INVALID_MARKS_FORMAT,
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}

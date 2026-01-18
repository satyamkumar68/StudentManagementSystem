CREATE DATABASE IF NOT EXISTS student_management_db;
USE student_management_db;

CREATE TABLE IF NOT EXISTS students (
    roll_number VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL,
    course VARCHAR(50) NOT NULL,
    marks DECIMAL(5,2) NOT NULL CHECK (marks >= 0 AND marks <= 100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO students (roll_number, name, email, phone, course, marks) VALUES
('2024001', 'Rahul Sharma', 'rahul.sharma@example.com', '9876543210', 'Computer Science', 85.50),
('2024002', 'Priya Patel', 'priya.patel@example.com', '9876543211', 'Information Technology', 92.00),
('2024003', 'Amit Kumar', 'amit.kumar@example.com', '9876543212', 'Electronics', 78.75);

SELECT * FROM students;

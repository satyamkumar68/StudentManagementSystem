package com.sms.model;

import com.sms.util.Constants;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Student Model Class (Enhanced POJO)
 * Represents a student entity with all attributes
 * Implements Comparable for sorting, includes builder pattern, validation, and
 * proper equals/hashCode
 */
public class Student implements Comparable<Student> {

    // Private fields (Encapsulation)
    private String rollNumber;
    private String name;
    private String email;
    private String phone;
    private String course;
    private double marks;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Default Constructor
    public Student() {
    }

    // Parameterized Constructor (for easy object creation)
    public Student(String rollNumber, String name, String email, String phone, String course, double marks) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.course = course;
        this.marks = marks;
    }

    // Builder Pattern for cleaner object creation
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String rollNumber;
        private String name;
        private String email;
        private String phone;
        private String course;
        private double marks;

        public Builder rollNumber(String rollNumber) {
            this.rollNumber = rollNumber;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder course(String course) {
            this.course = course;
            return this;
        }

        public Builder marks(double marks) {
            this.marks = marks;
            return this;
        }

        public Student build() {
            return new Student(rollNumber, name, email, phone, course, marks);
        }
    }

    // Getters and Setters with fluent interface
    public String getRollNumber() {
        return rollNumber;
    }

    public Student setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Student setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Student setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getCourse() {
        return course;
    }

    public Student setCourse(String course) {
        this.course = course;
        return this;
    }

    public double getMarks() {
        return marks;
    }

    public Student setMarks(double marks) {
        this.marks = marks;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Student setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Student setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    // Validation Methods
    public boolean isValidEmail() {
        return email != null && email.matches(Constants.Validation.EMAIL_PATTERN);
    }

    public boolean isValidPhone() {
        return phone != null && phone.matches(Constants.Validation.PHONE_PATTERN);
    }

    public boolean isValidMarks() {
        return marks >= Constants.Validation.MIN_MARKS && marks <= Constants.Validation.MAX_MARKS;
    }

    public boolean isValid() {
        return rollNumber != null && !rollNumber.trim().isEmpty() &&
                name != null && !name.trim().isEmpty() &&
                isValidEmail() &&
                isValidPhone() &&
                course != null && !course.trim().isEmpty() &&
                isValidMarks();
    }

    // Comparable implementation for sorting by roll number
    @Override
    public int compareTo(Student other) {
        if (other == null)
            return 1;
        if (this.rollNumber == null)
            return -1;
        if (other.rollNumber == null)
            return 1;
        return this.rollNumber.compareTo(other.rollNumber);
    }

    // equals and hashCode for proper object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Student student = (Student) o;
        return Objects.equals(rollNumber, student.rollNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber);
    }

    // toString method for easy debugging and display
    @Override
    public String toString() {
        return "Student{" +
                "rollNumber='" + rollNumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", course='" + course + '\'' +
                ", marks=" + marks +
                '}';
    }
}

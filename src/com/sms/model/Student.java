package com.sms.model;

import java.sql.Timestamp;

/**
 * Student Model Class (POJO - Plain Old Java Object)
 * Represents a student entity with all attributes
 * Follows JavaBeans conventions with getters and setters
 */
public class Student {
    
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
    
    // Getters and Setters
    public String getRollNumber() {
        return rollNumber;
    }
    
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public double getMarks() {
        return marks;
    }
    
    public void setMarks(double marks) {
        this.marks = marks;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
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

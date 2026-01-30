package org.studentsTest.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String studentId) {
        super("Student with ID " + studentId + " not found");
    }
}

package org.studentsTest.exception;

public class GroupNumAlreadyExistsException extends RuntimeException {
    public GroupNumAlreadyExistsException(String groupNumber) {
        super("Group is already exists with number: " + groupNumber);
    }
}

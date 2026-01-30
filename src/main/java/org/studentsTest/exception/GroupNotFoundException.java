package org.studentsTest.exception;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(String groupId) {
        super("Group with ID " + groupId + " not found");
    }
}

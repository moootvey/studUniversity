package org.studentsTest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.studentsTest.exception.GroupNotFoundException;
import org.studentsTest.exception.GroupNumAlreadyExistsException;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(GroupNumAlreadyExistsException.class)
    public ResponseEntity<String> handleGroupNumAlreadyExistsException(GroupNumAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<String> handleGroupNotFoundException(GroupNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

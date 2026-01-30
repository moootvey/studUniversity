package org.studentsTest.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.studentsTest.dto.request.AddStudentRequestDto;
import org.studentsTest.dto.response.AddStudentResponseDto;
import org.studentsTest.dto.response.DeleteStudentResponseDto;
import org.studentsTest.dto.response.GetStudentsResponseDto;
import org.studentsTest.service.interfaces.StudentService;

import java.net.URI;

@Controller()
@RequestMapping("/groups")
@Validated
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{groupId}")
    public String getStudentsView(@Size(min = 10, max = 10, message = "The group ID must be 10 characters long.") @PathVariable("groupId") String groupId,
                               Model model) {

        model.addAttribute("groupId", groupId);
        model.addAttribute("groupNum", studentService.getGroupNumberById(groupId));
        model.addAttribute("studentsDto", studentService.getStudentsByGroupId(groupId, 1));
        model.addAttribute("totalPages", studentService.getTotalStudentsPagesInGroup(groupId));
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);

        return "students";
    }

    @GetMapping("/{groupId}/getStudents")
    public ResponseEntity<GetStudentsResponseDto> getGroupStudents(@Size(min = 10, max = 10, message = "The group ID must be 10 characters long.") @PathVariable("groupId") String groupId,
                                                                         @Digits(message = "Page number must be a positive integer.", integer = 5, fraction = 0) @RequestParam(value = "page", defaultValue = "0") Integer page) {
        return ResponseEntity.ok().body(studentService.getStudentsByGroupId(groupId, page));
    }

    @PostMapping("/{groupId}/addStudent")
    @ResponseBody
    public ResponseEntity<AddStudentResponseDto> addStudentToGroup(@PathVariable("groupId") String groupId,
                                                                   @Valid @RequestBody AddStudentRequestDto addStudentRequestDto,
                                                                   UriComponentsBuilder uriComponentsBuilder) {

        URI location = uriComponentsBuilder.path("/{groupId}/add").buildAndExpand(groupId).toUri();
        return ResponseEntity.created(location).body(studentService.addStudent(groupId, addStudentRequestDto));
    }


    @DeleteMapping("/{groupId}/deleteStudent")
    @ResponseBody
    public ResponseEntity<DeleteStudentResponseDto> deleteStudentFromGroup(@Size(min = 10, max = 10, message = "The group ID must be 10 characters long.") @PathVariable("groupId") String groupId,
                                                                           @Size(min = 10, max = 10, message = "The student ID must be 10 characters long.") @RequestParam("studentId") String studentId) {
        return ResponseEntity.ok().body(studentService.deleteStudent(studentId));
    }
}

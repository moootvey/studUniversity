package org.studentsTest.service.interfaces;

import jakarta.validation.constraints.NotNull;
import org.studentsTest.dto.request.AddStudentRequestDto;
import org.studentsTest.dto.response.AddStudentResponseDto;
import org.studentsTest.dto.response.DeleteStudentResponseDto;
import org.studentsTest.dto.response.GetStudentsResponseDto;

public interface StudentService {
    GetStudentsResponseDto getStudentsByGroupId(@NotNull String groupId, @NotNull Integer page);
    Long getTotalStudentsPagesInGroup(@NotNull String groupId);
    AddStudentResponseDto addStudent(@NotNull String groupId, @NotNull AddStudentRequestDto addStudentRequestDto);
    DeleteStudentResponseDto deleteStudent(@NotNull String studentId);
    String getGroupNumberById(@NotNull String groupId);
}

package org.studentsTest.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.studentsTest.dto.request.AddStudentRequestDto;
import org.studentsTest.dto.response.AddStudentResponseDto;
import org.studentsTest.dto.response.DeleteStudentResponseDto;
import org.studentsTest.dto.response.GetStudentsResponseDto;
import org.studentsTest.dto.response.entity.GetStudentResponseDto;
import org.studentsTest.entity.StudentEntity;
import org.studentsTest.exception.GroupNotFoundException;
import org.studentsTest.exception.StudentNotFoundException;
import org.studentsTest.repository.GroupRepository;
import org.studentsTest.repository.StudentRepository;
import org.studentsTest.service.interfaces.StudentService;
import org.studentsTest.util.PageableUtil;
import org.studentsTest.util.UUIDUtil;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private static final String SORT_BY_STUDENT_NAME = "studentName";
    private static final int PAGE_SIZE = 10;

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Override
    public GetStudentsResponseDto getStudentsByGroupId(@NotNull String groupId, @NotNull Integer page) {
        Page<StudentEntity> studentPage = studentRepository.findByGroupId(groupId, PageableUtil.buildPageable(page, SORT_BY_STUDENT_NAME, Sort.Direction.ASC));
        List<GetStudentResponseDto> students = studentPage.stream()
                .map(student -> new GetStudentResponseDto(
                        student.getStudentId(),
                        student.getStudentName(),
                        student.getEnrollmentDate()
                ))
                .toList();
        return new GetStudentsResponseDto(students, page, studentPage.getTotalPages());
    }

    @Override
    public Long getTotalStudentsPagesInGroup(String groupId) {
        long total = studentRepository.countByGroupId(groupId);
        long result = total / PAGE_SIZE;
        if (total % PAGE_SIZE > 0)
            result += 1;
        return result;
    }

    @Override
    public AddStudentResponseDto addStudent(@NotNull String groupId, @NotNull AddStudentRequestDto addStudentRequestDto) {
        StudentEntity studentEntity = StudentEntity.builder()
                .studentId(UUIDUtil.generateUUID())
                .studentName(addStudentRequestDto.getStudentName())
                .enrollmentDate(new Timestamp(System.currentTimeMillis()))
                .group(groupRepository.findGroupByGroupId(groupId).orElseThrow(() -> new GroupNotFoundException(groupId)))
                .build();
        studentRepository.save(studentEntity);
        return new AddStudentResponseDto(studentEntity.getStudentId(), studentEntity.getStudentName());

    }

    @Override
    public DeleteStudentResponseDto deleteStudent(@NotNull String studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(studentId);
        }
        studentRepository.deleteById(studentId);
        return DeleteStudentResponseDto.builder().studentId(studentId).build();
    }

    @Override
    public String getGroupNumberById(String groupId) {
        return groupRepository.findGroupByGroupId(groupId).orElseThrow(() -> new GroupNotFoundException(groupId)).getGroupNumber();
    }
}

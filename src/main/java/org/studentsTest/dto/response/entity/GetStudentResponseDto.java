package org.studentsTest.dto.response.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetStudentResponseDto {
    private String studentId;
    private String studentName;
    private Timestamp enrollmentDate;
}

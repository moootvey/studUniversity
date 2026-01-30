package org.studentsTest.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddStudentResponseDto {
    private String studentId;
    private String studentName;
}

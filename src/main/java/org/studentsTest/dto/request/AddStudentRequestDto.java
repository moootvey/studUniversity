package org.studentsTest.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddStudentRequestDto {
    private String studentName;
}

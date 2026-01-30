package org.studentsTest.dto.response;

import lombok.*;
import org.studentsTest.dto.response.entity.GetStudentResponseDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetStudentsResponseDto {
    private List<GetStudentResponseDto> students;
    private long currentPage;
    private long totalPages;
}

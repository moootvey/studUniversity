package org.studentsTest.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetGroupResponseDto {
    private String groupId;
    private String groupNumber;
    private Long studentsCount;
}

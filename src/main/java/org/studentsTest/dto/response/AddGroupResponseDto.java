package org.studentsTest.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddGroupResponseDto {
    private String groupId;
    private String groupNumber;
}

package org.studentsTest.dto.database;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class GroupEntityWithStudentsCountDto {
    private final String groupId;
    private final String groupNumber;
    private final Long studentsCount;
}

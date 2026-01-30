package org.studentsTest.service.interfaces;

import jakarta.validation.constraints.NotNull;
import org.studentsTest.dto.request.AddGroupRequestDto;
import org.studentsTest.dto.response.AddGroupResponseDto;
import org.studentsTest.dto.response.GetGroupResponseDto;

import java.util.List;

public interface GroupService {
    List<GetGroupResponseDto> getAllGroups(Integer page);
    Long getTotalGroupsPages();
    AddGroupResponseDto addGroup(@NotNull AddGroupRequestDto addGroupRequestDto);
}

package org.studentsTest.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.studentsTest.dto.database.GroupEntityWithStudentsCountDto;
import org.studentsTest.dto.request.AddGroupRequestDto;
import org.studentsTest.dto.response.AddGroupResponseDto;
import org.studentsTest.dto.response.GetGroupResponseDto;
import org.studentsTest.entity.GroupEntity;
import org.studentsTest.exception.GroupNumAlreadyExistsException;
import org.studentsTest.repository.GroupRepository;
import org.studentsTest.repository.StudentRepository;
import org.studentsTest.service.interfaces.GroupService;
import org.studentsTest.util.PageableUtil;
import org.studentsTest.util.UUIDUtil;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private static final String SORT_BY_GROUP_NAME = "groupCreatedAt";
    private static final int PAGE_SIZE = 10;

    private final GroupRepository groupRepository;

    @Override
    public List<GetGroupResponseDto> getAllGroups(Integer page) {
        Page<GroupEntityWithStudentsCountDto> groups = groupRepository.findAllGroups(PageableUtil.buildPageable(page, SORT_BY_GROUP_NAME, Sort.Direction.DESC));
        return groups.stream()
                .map(group -> GetGroupResponseDto.builder()
                        .groupId(group.getGroupId())
                        .groupNumber(group.getGroupNumber())
                        .studentsCount(group.getStudentsCount())
                        .build())
                .toList();
    }

    @Override
    public Long getTotalGroupsPages() {
        long total = groupRepository.count();
        long result = total / PAGE_SIZE;
        if (total % PAGE_SIZE > 0)
            result += 1;
        return result;
    }

    @Override
    public AddGroupResponseDto addGroup(@NotNull AddGroupRequestDto addGroupRequestDto) {
        if (groupRepository.existsByGroupNumber(addGroupRequestDto.getGroupNumber())) {
            throw new GroupNumAlreadyExistsException(addGroupRequestDto.getGroupNumber());
        }

        GroupEntity groupEntity = GroupEntity.builder()
                .groupId(UUIDUtil.generateUUID())
                .groupNumber(addGroupRequestDto.getGroupNumber())
                .groupCreatedAt(new Timestamp(System.currentTimeMillis()))
                .build();

        groupRepository.save(groupEntity);

        return AddGroupResponseDto.builder()
                .groupId(groupEntity.getGroupId())
                .groupNumber(groupEntity.getGroupNumber())
                .build();
    }
}

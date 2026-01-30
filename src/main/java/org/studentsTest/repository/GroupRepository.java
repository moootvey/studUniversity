package org.studentsTest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.studentsTest.dto.database.GroupEntityWithStudentsCountDto;
import org.studentsTest.entity.GroupEntity;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity, String> {
    @Query("SELECT g FROM GroupEntity g WHERE g.groupId = :groupId")
    Optional<GroupEntity> findGroupByGroupId(String groupId);

    @Query("SELECT EXISTS (SELECT g FROM GroupEntity g WHERE g.groupNumber = :groupNumber) AS boolean_value")
    boolean existsByGroupNumber(String groupNumber);

    @Query("SELECT g.groupId, g.groupNumber, COUNT(s) FROM GroupEntity g LEFT JOIN g.students s GROUP BY g.groupId, g.groupNumber")
    Page<GroupEntityWithStudentsCountDto> findAllGroups(Pageable pageable);
}

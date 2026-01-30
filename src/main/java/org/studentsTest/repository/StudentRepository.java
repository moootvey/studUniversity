package org.studentsTest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.studentsTest.entity.StudentEntity;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, String> {
    @Query("SELECT s FROM StudentEntity s WHERE s.group.groupId = :groupId")
    Page<StudentEntity> findByGroupId(String groupId, Pageable pageable);

    @Query("SELECT COUNT(s) FROM StudentEntity s WHERE s.group.groupId = :groupId")
    Long countByGroupId(String groupId);


}

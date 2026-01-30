package org.studentsTest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student_groups")
public class GroupEntity {
    @Id
    @Column(name = "group_id", nullable = false, unique = true, length = 10)
    private String groupId;
    @Column(name = "group_number", nullable = false, unique = true, length = 20)
    private String groupNumber;
    @Column(name = "group_created_at", nullable = false)
    private Timestamp groupCreatedAt;

    @OneToMany(mappedBy = "group", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<StudentEntity> students;
}

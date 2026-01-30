package org.studentsTest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class StudentEntity {
    @Id
    @Column(name = "student_id", nullable = false, unique = true, length = 10)
    private String studentId;
    @Column(name = "student_name", nullable = false, length = 255)
    private String studentName;
    @Column(name = "enrollment_date", nullable = false)
    private Timestamp enrollmentDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_group_id", referencedColumnName = "group_id")
    private GroupEntity group;
}

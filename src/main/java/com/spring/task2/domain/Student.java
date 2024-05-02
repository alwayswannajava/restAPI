package com.spring.task2.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Override
    public String toString() {
        return "Student data: " +
                "studentId = " + studentId +
                ", groupId = " + groupId +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'';
    }
}

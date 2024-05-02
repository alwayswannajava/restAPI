package com.spring.task2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "groups")
public class Group {

    @Id
    private int groupId;

    @Column(name = "group_name")
    private String groupName;

    @Override
    public String toString() {
        return "Group data: " +
                "groupId = " + groupId +
                ", groupName = '" + groupName + '\'';
    }
}

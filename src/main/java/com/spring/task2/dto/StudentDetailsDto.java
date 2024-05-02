package com.spring.task2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class StudentDetailsDto {
    private long studentId;
    private Integer groupId;
    private String groupName;
    private String firstName;
    private String lastName;

}

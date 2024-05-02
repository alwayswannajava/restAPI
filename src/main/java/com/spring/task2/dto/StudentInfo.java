package com.spring.task2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class StudentInfo {
    private String groupName;
    private String firstName;
    private String lastName;
}

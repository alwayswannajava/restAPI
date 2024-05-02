package com.spring.task2.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Jacksonized
public class StudentPageResponse {

    private List<StudentInfo> studentInfosList;
    private int totalPages;
}

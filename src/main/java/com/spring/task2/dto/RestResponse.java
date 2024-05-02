package com.spring.task2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Jacksonized
public class RestResponse {

    private int successfulRecords;
    private int failedRecords;


}

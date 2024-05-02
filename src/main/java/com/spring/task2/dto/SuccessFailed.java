package com.spring.task2.dto;

import lombok.extern.jackson.Jacksonized;

public record SuccessFailed(
        int successfulRecords,
        int failedRecords
) {
}

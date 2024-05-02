package com.spring.task2.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;


@Getter
@Builder
@Jacksonized
public class StudentPageDto {

    private int pageNumber;

    private int pageSize;

    @NotBlank(message = "Key is blank")
    @NotNull(message = "Key is null")
    private String key;

    @NotBlank(message = "Operation is blank")
    @NotNull(message = "Operation is null")
    private String operation;

    private Object value;

}

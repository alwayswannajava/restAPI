package com.spring.task2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class GroupSaveDto {
    public static final String GROUP_NAME_REGEX_PATTERN = "([A-Z]{2})-([0-9]{2})";

    private Integer groupId;

    @NotBlank(message = "Group name is blank")
    @Pattern(regexp = GROUP_NAME_REGEX_PATTERN, message = "Group name must be like 2 big letters " +
            "then '-' symbol, then 2 digits. For example, correct groupName: AW-22'")
    private String groupName;
}

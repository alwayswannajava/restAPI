package com.spring.task2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class StudentSaveDto {
    public static final String FIRST_NAME_AND_LAST_NAME_REGEX = "[A-Z][a-z]*";

    private Integer groupId;

    @Pattern(regexp = GroupSaveDto.GROUP_NAME_REGEX_PATTERN, message = "Group name must be like 2 letters" +
            "then '-' symbol, then 2 digits. For example, correct groupName: AW-22'")
    private String groupName;

    @NotNull(message = "firstName is null")
    @NotBlank(message = "firstName is blank")
    @Size(min = 2, max = 30, message = "first name size must be between 2 and 30")
    @Pattern(regexp = FIRST_NAME_AND_LAST_NAME_REGEX, message = "First name isn't correct. " +
            "Must be start with uppercase letter")
    private String firstName;

    @NotNull(message = "lastName is null")
    @NotBlank(message = "lastName is blank")
    @Size(min = 2, max = 30, message = "last name size must be between 2 and 30")
    @Pattern(regexp = FIRST_NAME_AND_LAST_NAME_REGEX, message = "First name isn't correct. " +
            "Must be start with uppercase letter")
    private String lastName;


}

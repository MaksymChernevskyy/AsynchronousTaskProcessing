package com.task.asynchronous.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateTaskDto {
    @NotBlank(message = "input cannot be blank")
    private String input;
    @NotBlank(message = "pattern cannot be blank")
    private String pattern;
}

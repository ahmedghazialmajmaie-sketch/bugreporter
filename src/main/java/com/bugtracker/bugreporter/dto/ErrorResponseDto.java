package com.bugtracker.bugreporter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {

    private int status;
    private String error;
    private String message;
    private Map<String, String> fieldErrors; // null unless it's a validation error
    private LocalDateTime timestamp;
}
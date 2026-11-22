package com.bugtracker.bugreporter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponseDto {

    private String accessToken;
    private String tokenType;   // "Bearer"
    private long expiresInSeconds;
    private UserResponseDto user;
}

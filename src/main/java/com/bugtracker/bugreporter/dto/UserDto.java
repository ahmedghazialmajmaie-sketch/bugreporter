package com.bugtracker.bugreporter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer userId;
    private String userEmail;
    private String passwordHashed;
    private String username;
    private String profilePicturePath;
    private Boolean userAccountStatus;
    private LocalDateTime accountCreationDate;
}
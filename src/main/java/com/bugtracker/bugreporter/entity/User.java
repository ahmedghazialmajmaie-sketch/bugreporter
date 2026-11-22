package com.bugtracker.bugreporter.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "Users_email_ak",
                        columnNames = "user_email"
                ),
                @UniqueConstraint(
                        name = "Users_username_ak",
                        columnNames = "username"
                )
        }
)
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_email", nullable = false, length = 40)
    private String userEmail;

    @Column(name = "password_hashed", nullable = false, length = 255)
    private String passwordHashed;

    @Column(name = "username", nullable = false, length = 15)
    private String username;

    @Column(name = "profile_picture_path", length = 200)
    private String profilePicturePath;

    @Column(name = "user_account_status", nullable = false)
    private Boolean userAccountStatus;

    @Column(name = "account_creation_date", nullable = false)
    private LocalDateTime accountCreationDate;

    public User() {
    }
}

package com.bugtracker.bugreporter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer notificationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(
            name = "notification_content",
            nullable = false,
            length = 100
    )
    private String notificationContent;

    @Column(
            name = "notification_title",
            nullable = false,
            length = 100
    )
    private String notificationTitle;

    @Column(name = "notification_date", nullable = false)
    private LocalDateTime notificationDate;
}

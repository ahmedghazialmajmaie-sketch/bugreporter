package com.bugtracker.bugreporter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "developers")
@Getter
@Setter
public class Developer {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_bug_id")
    private Bug assignedBug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_manager_id")
    private Manager manager;
}
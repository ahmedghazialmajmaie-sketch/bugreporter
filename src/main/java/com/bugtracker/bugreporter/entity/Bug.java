package com.bugtracker.bugreporter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bugs")
@Getter
@Setter
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bug_id")
    private Integer bugId;

    @Column(name = "priority", nullable = false, length = 10)
    private String priority;

    @Column(name = "severity", nullable = false, length = 10)
    private String severity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "bug_status", nullable = false, length = 10)
    private String bugStatus;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    @Column(name = "OS", nullable = false, length = 10)
    private String os;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter;

    @Column(name = "bug_summery", nullable = false, length = 50)
    private String bugSummary;

    @Column(name = "replication_steps", length = 500)
    private String replicationSteps;

    @Column(name = "bug_description", length = 1000)
    private String bugDescription;

    @Column(name = "bug_type", length = 10)
    private String bugType;
}

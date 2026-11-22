package com.bugtracker.bugreporter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "logcategories")
@Getter
@Setter
public class LogCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_category_id")
    private Integer logCategoryId;

    @Column(name = "Log_Category", nullable = false, length = 30)
    private String logCategory;
}

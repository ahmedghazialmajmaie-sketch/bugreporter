package com.bugtracker.bugreporter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class ComponentId implements Serializable {

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "component_name", length = 50)
    private String componentName;

    public ComponentId() {
    }

    public ComponentId(Integer projectId, String componentName) {
        this.projectId = projectId;
        this.componentName = componentName;
    }
}

package com.bugtracker.bugreporter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "components")
@Getter
@Setter
public class ProjectComponent {

    @EmbeddedId
    private ComponentId id;

    @MapsId("projectId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public ProjectComponent() {
    }

    public ProjectComponent(Project project, String componentName) {
        this.project = project;
        this.id = new ComponentId(
                project.getProjectId(),
                componentName
        );
    }

    @Transient
    public String getComponentName() {
        return id == null ? null : id.getComponentName();
    }
}

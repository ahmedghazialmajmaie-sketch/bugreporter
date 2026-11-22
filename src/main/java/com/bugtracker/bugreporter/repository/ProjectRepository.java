package com.bugtracker.bugreporter.repository;

import com.bugtracker.bugreporter.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    // Find project by name
    Optional<Project> findByProjectName(String projectName);

    // Check whether a project name exists
    boolean existsByProjectName(String projectName);

    // Find projects assigned to a specific manager
    List<Project> findByProjectManager_UserId(Integer managerId);

    // Find projects by version
    List<Project> findByProjectVersion(String projectVersion);
}

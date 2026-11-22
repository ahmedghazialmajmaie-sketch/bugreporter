package com.bugtracker.bugreporter.repository;

import com.bugtracker.bugreporter.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugRepository extends JpaRepository<Bug, Integer> {

    // Find bugs belonging to a project
    List<Bug> findByProject_ProjectId(Integer projectId);

    // Find bugs reported by a specific user
    List<Bug> findByReporter_UserId(Integer reporterId);

    // Find bugs by status
    List<Bug> findByBugStatus(String bugStatus);

    // Find bugs by priority
    List<Bug> findByPriority(String priority);

    // Find bugs by severity
    List<Bug> findBySeverity(String severity);

    // Find bugs assigned to a project and having a specific status
    List<Bug> findByProject_ProjectIdAndBugStatus(
            Integer projectId,
            String bugStatus
    );

    // Find bugs by reporter and status
    List<Bug> findByReporter_UserIdAndBugStatus(
            Integer reporterId,
            String bugStatus
    );

    // Count bugs belonging to a project
    long countByProject_ProjectId(Integer projectId);

    // Count bugs by status
    long countByBugStatus(String bugStatus);
}

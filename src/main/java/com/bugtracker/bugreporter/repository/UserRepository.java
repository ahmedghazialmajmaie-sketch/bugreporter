package com.bugtracker.bugreporter.repository;

import com.bugtracker.bugreporter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Find user by email
    Optional<User> findByUserEmail(String userEmail);

    // Find user by username
    Optional<User> findByUsername(String username);

    // Check whether an email already exists
    boolean existsByUserEmail(String userEmail);

    // Check whether a username already exists
    boolean existsByUsername(String username);

    // get hashed password by email
    @Query("SELECT u.passwordHashed FROM User u WHERE u.userEmail = :userEmail")
    Optional<String> findPasswordHashedByUserEmail(@Param("userEmail") String userEmail);
}

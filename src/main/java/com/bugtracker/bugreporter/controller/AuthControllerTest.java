package com.bugtracker.bugreporter.controller;

import com.bugtracker.bugreporter.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test/auth")
@RequiredArgsConstructor
public class AuthControllerTest {

    private final AuthService authService;


    // use this endpoint for testing purposes only. Remove in production @Ahmed Ghazi.
    // We are getting the currently injected user from the spring security context
    // the @AuthenticationPrincipal calls the (Authentication.getPrincipal()) as a method argument.
    // It eliminates the boilerplate of manually querying SecurityContextHolder.getContext().getAuthentication()
    // Comment by @Ahmed Ghazi
    @GetMapping("/me")
    public ResponseEntity<String> me(@AuthenticationPrincipal Integer userId) {
        return ResponseEntity.ok("Authenticated as user " + userId);
    }
}
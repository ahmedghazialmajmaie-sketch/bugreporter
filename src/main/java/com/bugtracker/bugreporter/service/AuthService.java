package com.bugtracker.bugreporter.service;

import com.bugtracker.bugreporter.dto.AuthResponseDto;
import com.bugtracker.bugreporter.dto.LoginRequestDto;
import com.bugtracker.bugreporter.dto.RegisterRequestDto;
import com.bugtracker.bugreporter.dto.UserResponseDto;
import com.bugtracker.bugreporter.entity.User;
import com.bugtracker.bugreporter.exception.DuplicateResourceException;
import com.bugtracker.bugreporter.exception.InvalidCredentialsException;
import com.bugtracker.bugreporter.repository.UserRepository;
import com.bugtracker.bugreporter.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;


    @Transactional
    public UserResponseDto register(RegisterRequestDto request) {
        String email = request.getUserEmail().trim().toLowerCase();

        if (userRepository.existsByUserEmail(email)) {
            throw new DuplicateResourceException("Email is already registered");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username is already taken");
        }

        User user = new User();
        user.setUserEmail(email);
        user.setUsername(request.getUsername());
        user.setPasswordHashed(passwordEncoder.encode(request.getPassword()));
        user.setUserAccountStatus(true);
        user.setAccountCreationDate(LocalDateTime.now());

        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserResponseDto.class);
    }

    @Transactional(readOnly = true)
    public AuthResponseDto login(LoginRequestDto request) {
        String email = request.getUserEmail().trim().toLowerCase();

        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        /*
        Disabled account now returns the generic message.
        In the previous version it said "Account is disabled", which tells someone who has the correct password that the account exists and is disabled.
        Since the password check already passed at that point, the leak is minor
         */

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHashed())
                || !Boolean.TRUE.equals(user.getUserAccountStatus())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponseDto(
                token,
                "Bearer",
                jwtService.getExpirationMs() / 1000,
                modelMapper.map(user, UserResponseDto.class)
        );
    }
}
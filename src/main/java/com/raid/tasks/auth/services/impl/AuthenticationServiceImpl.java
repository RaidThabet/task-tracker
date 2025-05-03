package com.raid.tasks.auth.services.impl;

import com.raid.tasks.auth.AuthenticationRequest;
import com.raid.tasks.auth.AuthenticationResponse;
import com.raid.tasks.auth.RegisterRequest;
import com.raid.tasks.auth.services.AuthenticationService;
import com.raid.tasks.domain.entities.User;
import com.raid.tasks.domain.entities.UserRole;
import com.raid.tasks.repositories.UserRepository;
import com.raid.tasks.security.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = new User(
                null,
                request.getFullName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                UserRole.ROLE_USER,
                null,
                null,
                null
        );
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}

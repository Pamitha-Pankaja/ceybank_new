package com.example.ceybank.auth.controllers;

import com.example.ceybank.auth.jwt.JwtUtil;
import com.example.ceybank.auth.models.*;
import com.example.ceybank.auth.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        Set<RoleName> roles = new HashSet<>();
        for (String role : request.getRoles()) {
            roles.add(RoleName.valueOf(role));
        }

        User user = authService.registerUser(request.getUsername(), request.getPassword(), roles);
        return ResponseEntity.ok("User registered successfully");
    }

    // Add login with JWT (I'll include this next)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken((UserDetails) authentication.getPrincipal());

        return ResponseEntity.ok(new JwtResponse(token));
    }
}

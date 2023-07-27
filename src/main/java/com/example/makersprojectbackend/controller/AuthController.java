package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.security.JwtUtil;
import com.example.makersprojectbackend.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User registrationRequest) {
        if (authService.isPresentEmail(registrationRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Не верный email");
        }
        authService.register(registrationRequest);
        return ResponseEntity.ok("Вы зарегистрировались!");
    }

    @PostMapping("/auth")
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody User authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String accessToken = jwtUtil.generateToken(authRequest.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(authRequest.getEmail());

            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", accessToken);
            tokens.put("refresh_token", refreshToken);

            return ResponseEntity.ok(tokens);
        } else {
            throw new UsernameNotFoundException("Неверная почта или пароль");
        }
    }

    @GetMapping("/reset")
    public String resetPassword(@RequestParam String email) {
        return authService.resetPassword(email);
    }

    @PostMapping("/reset/{resetToken}")
    public String updatePassword(@PathVariable String resetToken, @RequestParam String password) {
        return authService.saveNewPassword(resetToken, password);
    }
}

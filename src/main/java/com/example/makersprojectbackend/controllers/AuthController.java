package com.example.makersprojectbackend.controllers;

import com.example.makersprojectbackend.entities.User;
import com.example.makersprojectbackend.security.JwtUtil;
import com.example.makersprojectbackend.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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
    public String authenticate(@RequestBody User authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(authRequest.getEmail());
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

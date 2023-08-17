package com.example.makersprojectbackend.controller.security;

import com.example.makersprojectbackend.dto.UserDto;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.mappers.UserMapper;
import com.example.makersprojectbackend.security.JwtUtil;
import com.example.makersprojectbackend.service.security.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authorization", description = "register, login, reset password, update password")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final UserMapper userMapper;


    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserDto registrationRequest) {
        if (authService.isPresentEmail(registrationRequest.getEmail())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "User with email: " + registrationRequest.getEmail() + " already exists!"));
        }

        User user = userMapper.convertToEntity(registrationRequest);
        authService.register(user);

        String accessToken = jwtUtil.generateToken(registrationRequest.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(registrationRequest.getEmail());

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);

        return ResponseEntity.ok(tokens);
    }



    @PostMapping("/auth")
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody UserDto authRequest) {
        try {
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
        } catch (AuthenticationException e) {
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

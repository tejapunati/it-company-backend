package com.ssrmtech.itcompany.controller;

import com.ssrmtech.itcompany.dto.JwtResponse;
import com.ssrmtech.itcompany.dto.LoginRequest;
import com.ssrmtech.itcompany.dto.UserRegistrationRequest;
import com.ssrmtech.itcompany.entity.User;
import com.ssrmtech.itcompany.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        JwtResponse response = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationRequest request) {
        String result = authService.registerUser(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/admin-register")
    public ResponseEntity<String> adminRegister(@RequestBody UserRegistrationRequest request) {
        User admin = authService.registerAdmin(request);
        return ResponseEntity.ok("Admin registered successfully. Awaiting parent admin approval.");
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // JWT is stateless, so we don't need to do anything server-side
        // The client will remove the token
        return ResponseEntity.ok("Logged out successfully");
    }
}
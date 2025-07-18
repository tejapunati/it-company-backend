package com.ssrmtech.itcompany.service;

import com.ssrmtech.itcompany.dto.JwtResponse;
import com.ssrmtech.itcompany.dto.LoginRequest;
import com.ssrmtech.itcompany.dto.UserRegistrationRequest;
import com.ssrmtech.itcompany.entity.Role;
import com.ssrmtech.itcompany.entity.Status;
import com.ssrmtech.itcompany.entity.User;
import com.ssrmtech.itcompany.repository.UserRepository;
import com.ssrmtech.itcompany.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    EmailService emailService;

    @Autowired
    ActivityService activityService;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = (User) authentication.getPrincipal();
        
        // Update last login
        user.setLastLogin(java.time.LocalDateTime.now());
        userRepository.save(user);

        // Log activity
        activityService.logActivity(user, "User logged in");

        return new JwtResponse(jwt, user.getId(), user.getName(), user.getEmail(), user.getRole().name());
    }

    public String registerUser(UserRegistrationRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Error: Email is already taken!");
        }

        // Create new user account
        User user = new User(signUpRequest.getName(),
                           signUpRequest.getEmail(),
                           encoder.encode(signUpRequest.getPassword()),
                           Role.valueOf(signUpRequest.getRole().toUpperCase()));

        user.setPhone(signUpRequest.getPhone());
        user.setDepartment(signUpRequest.getDepartment());

        userRepository.save(user);

        // Send notification email to admin
        emailService.sendUserRegistrationNotification(user);

        // Log activity
        activityService.logActivity(user, "New user registered: " + user.getName());

        return "User registered successfully!";
    }

    public User registerAdmin(UserRegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User admin = new User();
        admin.setName(request.getName());
        admin.setEmail(request.getEmail());
        admin.setPassword(encoder.encode(request.getPassword()));
        admin.setPhone(request.getPhone());
        admin.setDepartment(request.getDepartment());
        admin.setRole(Role.ADMIN);
        admin.setStatus(Status.PENDING);

        User saved = userRepository.save(admin);
        emailService.sendAdminApprovalNotification(saved);
        activityService.logActivity(saved, "Admin registration submitted");

        return saved;
    }

    public java.util.List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public java.util.List<User> getPendingUsers() {
        return userRepository.findByStatus(Status.PENDING);
    }

    public void approveUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        emailService.sendUserApprovalEmail(user, "approved");
        activityService.logActivity(user, "User approved");
    }

    public void rejectUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(Status.INACTIVE);
        userRepository.save(user);
        emailService.sendUserApprovalEmail(user, "rejected");
        activityService.logActivity(user, "User rejected");
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
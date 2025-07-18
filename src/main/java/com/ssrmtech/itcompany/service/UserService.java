package com.ssrmtech.itcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssrmtech.itcompany.model.User;
import com.ssrmtech.itcompany.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailService emailService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User createUser(User user) {
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Set status based on role
        if (user.getRole().equals("ADMIN")) {
            user.setStatus("PENDING"); // Admin needs approval
        } else if (user.getRole().equals("USER")) {
            user.setStatus("PENDING"); // User needs approval
        } else if (user.getRole().equals("PARENT_ADMIN")) {
            user.setStatus("ACTIVE"); // Parent admin is active by default
        }
        
        User savedUser = userRepository.save(user);
        
        // Send email notification to admins
        if (user.getRole().equals("ADMIN") || user.getRole().equals("USER")) {
            emailService.sendRegistrationNotification(savedUser);
        }
        
        return savedUser;
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setDepartment(userDetails.getDepartment());
        user.setPhone(userDetails.getPhone());
        
        // Only update password if provided
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
    
    public User approveUser(Long id) {
        User user = getUserById(id);
        user.setStatus("ACTIVE");
        User savedUser = userRepository.save(user);
        
        // Send approval email
        emailService.sendApprovalNotification(savedUser);
        
        return savedUser;
    }
    
    public User rejectUser(Long id) {
        User user = getUserById(id);
        user.setStatus("INACTIVE");
        User savedUser = userRepository.save(user);
        
        // Send rejection email
        emailService.sendRejectionNotification(savedUser);
        
        return savedUser;
    }
}
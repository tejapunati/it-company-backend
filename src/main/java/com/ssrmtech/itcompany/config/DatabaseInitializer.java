package com.ssrmtech.itcompany.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssrmtech.itcompany.model.User;
import com.ssrmtech.itcompany.repository.UserRepository;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if parent admin exists
            if (userRepository.findByEmail("parent-admin@ssrmtech.com").isEmpty()) {
                // Create parent admin user
                User parentAdmin = new User();
                parentAdmin.setName("Parent Admin");
                parentAdmin.setEmail("parent-admin@ssrmtech.com");
                parentAdmin.setPassword(passwordEncoder.encode("admin123"));
                parentAdmin.setRole("PARENT_ADMIN");
                parentAdmin.setStatus("ACTIVE");
                parentAdmin.setDepartment("Administration");
                parentAdmin.setPhone("123-456-7890");
                
                userRepository.save(parentAdmin);
                
                System.out.println("Parent Admin created with email: parent-admin@ssrmtech.com and password: admin123");
            }
        };
    }
}
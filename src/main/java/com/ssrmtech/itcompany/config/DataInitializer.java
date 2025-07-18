package com.ssrmtech.itcompany.config;

import com.ssrmtech.itcompany.entity.Role;
import com.ssrmtech.itcompany.entity.Status;
import com.ssrmtech.itcompany.entity.User;
import com.ssrmtech.itcompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        try {
            // Create default users if they don't exist
            if (userRepository.count() == 0) {
                createDefaultUsers();
            }
        } catch (Exception e) {
            // Tables might not be created yet, this is expected on first run
            System.out.println("Database initialization will be handled by JPA");
        }
    }

    private void createDefaultUsers() {
        // Create Parent Admin
        User parentAdmin = new User();
        parentAdmin.setName("Parent Admin");
        parentAdmin.setEmail("parentadmin@ssrmtech.com");
        parentAdmin.setPassword(passwordEncoder.encode("password123"));
        parentAdmin.setRole(Role.PARENT_ADMIN);
        parentAdmin.setStatus(Status.ACTIVE);
        parentAdmin.setDepartment("Management");
        userRepository.save(parentAdmin);

        // Create Admin
        User admin = new User();
        admin.setName("Admin User");
        admin.setEmail("admin@ssrmtech.com");
        admin.setPassword(passwordEncoder.encode("password123"));
        admin.setRole(Role.ADMIN);
        admin.setStatus(Status.ACTIVE);
        admin.setDepartment("IT");
        userRepository.save(admin);

        // Create Regular User
        User user = new User();
        user.setName("John Doe");
        user.setEmail("user@ssrmtech.com");
        user.setPassword(passwordEncoder.encode("password123"));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        user.setDepartment("Development");
        userRepository.save(user);

        System.out.println("✅ Default users created:");
        System.out.println("📧 Parent Admin: parentadmin@ssrmtech.com / password123");
        System.out.println("📧 Admin: admin@ssrmtech.com / password123");
        System.out.println("📧 User: user@ssrmtech.com / password123");
    }
}
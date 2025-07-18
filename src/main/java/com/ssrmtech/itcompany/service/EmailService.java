package com.ssrmtech.itcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssrmtech.itcompany.model.EmailLog;
import com.ssrmtech.itcompany.model.User;
import com.ssrmtech.itcompany.repository.EmailLogRepository;
import com.ssrmtech.itcompany.repository.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailLogRepository emailLogRepository;
    
    @Autowired
    private UserRepository userRepository;

    public void sendRegistrationNotification(User user) {
        // Find all admins and parent admins
        List<User> admins = userRepository.findByRoleIn(List.of("ADMIN", "PARENT_ADMIN"));
        
        for (User admin : admins) {
            EmailLog emailLog = new EmailLog();
            emailLog.setToEmail(admin.getEmail());
            emailLog.setFromEmail("noreply@ssrmtech.com");
            emailLog.setSubject("New User Registration");
            
            String body = "Dear " + admin.getName() + ",\n\n" +
                    "A new user has registered and requires your approval:\n\n" +
                    "Name: " + user.getName() + "\n" +
                    "Email: " + user.getEmail() + "\n" +
                    "Role: " + user.getRole() + "\n\n" +
                    "Please log in to the admin dashboard to approve or reject this user.\n\n" +
                    "Regards,\nSSRM Tech Solution";
            
            emailLog.setBody(body);
            emailLog.setType(user.getRole().equals("ADMIN") ? "ADMIN_APPROVAL" : "USER_APPROVAL");
            emailLog.setStatus("PENDING");
            emailLog.setSentDate(new Date());
            
            emailLogRepository.save(emailLog);
        }
    }
    
    public void sendApprovalNotification(User user) {
        EmailLog emailLog = new EmailLog();
        emailLog.setToEmail(user.getEmail());
        emailLog.setFromEmail("noreply@ssrmtech.com");
        emailLog.setSubject("Account Approved");
        
        String body = "Dear " + user.getName() + ",\n\n" +
                "Your account has been approved. You can now log in to the system.\n\n" +
                "Regards,\nSSRM Tech Solution";
        
        emailLog.setBody(body);
        emailLog.setType(user.getRole().equals("ADMIN") ? "ADMIN_APPROVED" : "USER_APPROVED");
        emailLog.setStatus("PENDING");
        emailLog.setSentDate(new Date());
        
        emailLogRepository.save(emailLog);
    }
    
    public void sendRejectionNotification(User user) {
        EmailLog emailLog = new EmailLog();
        emailLog.setToEmail(user.getEmail());
        emailLog.setFromEmail("noreply@ssrmtech.com");
        emailLog.setSubject("Account Rejected");
        
        String body = "Dear " + user.getName() + ",\n\n" +
                "We regret to inform you that your account registration has been rejected.\n\n" +
                "Regards,\nSSRM Tech Solution";
        
        emailLog.setBody(body);
        emailLog.setType(user.getRole().equals("ADMIN") ? "ADMIN_REJECTED" : "USER_REJECTED");
        emailLog.setStatus("PENDING");
        emailLog.setSentDate(new Date());
        
        emailLogRepository.save(emailLog);
    }
}
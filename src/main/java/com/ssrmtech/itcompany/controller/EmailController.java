package com.ssrmtech.itcompany.controller;

import com.ssrmtech.itcompany.entity.EmailLog;
import com.ssrmtech.itcompany.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emails")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PARENT_ADMIN')")
    public ResponseEntity<List<EmailLog>> getAllEmails() {
        List<EmailLog> emails = emailService.getAllEmails();
        return ResponseEntity.ok(emails);
    }

    @GetMapping("/my")
    public ResponseEntity<List<EmailLog>> getMyEmails(Authentication auth) {
        List<EmailLog> emails = emailService.getMyEmails(auth.getName());
        return ResponseEntity.ok(emails);
    }
}
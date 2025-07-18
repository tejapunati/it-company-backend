package com.ssrmtech.itcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssrmtech.itcompany.model.EmailLog;
import com.ssrmtech.itcompany.repository.EmailLogRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/email-logs")
public class EmailLogController {

    @Autowired
    private EmailLogRepository emailLogRepository;

    @GetMapping
    public ResponseEntity<List<EmailLog>> getAllEmailLogs() {
        return ResponseEntity.ok(emailLogRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EmailLog> getEmailLogById(@PathVariable Long id) {
        return ResponseEntity.ok(emailLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Email log not found with id: " + id)));
    }
    
    @GetMapping("/user/{email}")
    public ResponseEntity<List<EmailLog>> getEmailLogsByUserEmail(@PathVariable String email) {
        return ResponseEntity.ok(emailLogRepository.findByToEmail(email));
    }
}
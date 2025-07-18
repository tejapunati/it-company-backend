package com.ssrmtech.itcompany.controller;

import com.ssrmtech.itcompany.entity.Status;
import com.ssrmtech.itcompany.entity.TimesheetStatus;
import com.ssrmtech.itcompany.repository.TimesheetRepository;
import com.ssrmtech.itcompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stats")
@CrossOrigin(origins = "*")
public class StatsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PARENT_ADMIN')")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Total users
        long totalUsers = userRepository.count();
        stats.put("totalUsers", totalUsers);
        
        // Pending approvals (users + timesheets)
        long pendingUsers = userRepository.countByStatus(Status.PENDING);
        long pendingTimesheets = timesheetRepository.countByStatus(TimesheetStatus.PENDING);
        stats.put("pendingApprovals", pendingUsers + pendingTimesheets);
        
        // Monthly hours (from approved timesheets in current month)
        LocalDateTime firstDayOfMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime lastDayOfMonth = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());
        
        Double monthlyHours = timesheetRepository.sumTotalHoursByStatusAndSubmittedDateBetween(
            TimesheetStatus.APPROVED, firstDayOfMonth, lastDayOfMonth);
        stats.put("monthlyHours", monthlyHours != null ? monthlyHours : 0);
        
        // Active projects (simulate based on users)
        long activeProjects = Math.max(1, totalUsers / 4);
        stats.put("activeProjects", activeProjects);
        
        return ResponseEntity.ok(stats);
    }
}
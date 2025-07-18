package com.ssrmtech.itcompany.controller;

import com.ssrmtech.itcompany.dto.DashboardStats;
import com.ssrmtech.itcompany.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/admin/stats")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PARENT_ADMIN')")
    public ResponseEntity<DashboardStats> getAdminStats() {
        DashboardStats stats = dashboardService.getAdminStats();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/user/stats")
    public ResponseEntity<DashboardStats> getUserStats(Authentication auth) {
        DashboardStats stats = dashboardService.getUserStats(auth.getName());
        return ResponseEntity.ok(stats);
    }
}
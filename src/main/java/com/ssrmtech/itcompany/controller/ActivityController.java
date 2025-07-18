package com.ssrmtech.itcompany.controller;

import com.ssrmtech.itcompany.entity.Activity;
import com.ssrmtech.itcompany.entity.User;
import com.ssrmtech.itcompany.repository.UserRepository;
import com.ssrmtech.itcompany.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activities")
@CrossOrigin(origins = "*")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/recent")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PARENT_ADMIN')")
    public ResponseEntity<List<Activity>> getRecentActivities() {
        List<Activity> activities = activityService.getRecentActivities();
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Activity>> getMyActivities(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Activity> activities = activityService.getMyActivities(user);
        return ResponseEntity.ok(activities);
    }
}
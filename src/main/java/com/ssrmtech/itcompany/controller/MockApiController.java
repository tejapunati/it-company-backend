package com.ssrmtech.itcompany.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class MockApiController {

    private final List<Map<String, Object>> timesheets = new ArrayList<>();

    public MockApiController() {
        // Create some sample timesheets
        Map<String, Object> timesheet1 = new HashMap<>();
        timesheet1.put("id", 1);
        timesheet1.put("userId", 1);
        timesheet1.put("weekEnding", LocalDate.now().toString());
        timesheet1.put("status", "PENDING");
        timesheet1.put("totalHours", 40);
        Map<String, Double> hours1 = new HashMap<>();
        hours1.put("MONDAY", 8.0);
        hours1.put("TUESDAY", 8.0);
        hours1.put("WEDNESDAY", 8.0);
        hours1.put("THURSDAY", 8.0);
        hours1.put("FRIDAY", 8.0);
        hours1.put("SATURDAY", 0.0);
        hours1.put("SUNDAY", 0.0);
        timesheet1.put("hours", hours1);
        timesheet1.put("submittedDate", LocalDateTime.now().toString());
        timesheet1.put("comments", "");

        Map<String, Object> timesheet2 = new HashMap<>();
        timesheet2.put("id", 2);
        timesheet2.put("userId", 2);
        timesheet2.put("weekEnding", LocalDate.now().toString());
        timesheet2.put("status", "PENDING");
        timesheet2.put("totalHours", 35);
        Map<String, Double> hours2 = new HashMap<>();
        hours2.put("MONDAY", 7.0);
        hours2.put("TUESDAY", 7.0);
        hours2.put("WEDNESDAY", 7.0);
        hours2.put("THURSDAY", 7.0);
        hours2.put("FRIDAY", 7.0);
        hours2.put("SATURDAY", 0.0);
        hours2.put("SUNDAY", 0.0);
        timesheet2.put("hours", hours2);
        timesheet2.put("submittedDate", LocalDateTime.now().toString());
        timesheet2.put("comments", "");

        timesheets.add(timesheet1);
        timesheets.add(timesheet2);
    }

    @GetMapping("/mock-health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Backend service is running");
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/timesheets/pending")
    public ResponseEntity<List<Map<String, Object>>> getPendingTimesheets() {
        return ResponseEntity.ok(timesheets);
    }

    @PutMapping("/timesheets/{id}/approve")
    public ResponseEntity<Map<String, Object>> approveTimesheet(@PathVariable Integer id) {
        Optional<Map<String, Object>> timesheetOpt = timesheets.stream()
                .filter(t -> t.get("id").equals(id))
                .findFirst();

        if (timesheetOpt.isPresent()) {
            Map<String, Object> timesheet = timesheetOpt.get();
            timesheet.put("status", "APPROVED");
            timesheet.put("reviewedDate", LocalDateTime.now().toString());
            timesheet.put("reviewedBy", 1); // Admin ID
            timesheet.put("comments", "Approved by admin");
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Timesheet approved successfully");
            response.put("timesheet", timesheet);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/timesheets/{id}/approve")
    public ResponseEntity<Map<String, Object>> approveTimesheetPost(@PathVariable Integer id) {
        return approveTimesheet(id);
    }

    @PutMapping("/timesheets/{id}/reject")
    public ResponseEntity<Map<String, Object>> rejectTimesheet(@PathVariable Integer id, @RequestBody(required = false) Map<String, String> request) {
        Optional<Map<String, Object>> timesheetOpt = timesheets.stream()
                .filter(t -> t.get("id").equals(id))
                .findFirst();

        if (timesheetOpt.isPresent()) {
            Map<String, Object> timesheet = timesheetOpt.get();
            timesheet.put("status", "REJECTED");
            timesheet.put("reviewedDate", LocalDateTime.now().toString());
            timesheet.put("reviewedBy", 1); // Admin ID
            
            String comments = "Rejected by admin";
            if (request != null && request.containsKey("comments")) {
                comments = request.get("comments");
            }
            timesheet.put("comments", comments);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Timesheet rejected successfully");
            response.put("timesheet", timesheet);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/timesheets/{id}/reject")
    public ResponseEntity<Map<String, Object>> rejectTimesheetPost(@PathVariable Integer id, @RequestBody(required = false) Map<String, String> request) {
        return rejectTimesheet(id, request);
    }
}
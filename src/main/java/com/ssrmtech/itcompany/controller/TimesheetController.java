package com.ssrmtech.itcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssrmtech.itcompany.model.Timesheet;
import com.ssrmtech.itcompany.service.TimesheetService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/timesheets")
public class TimesheetController {

    @Autowired
    private TimesheetService timesheetService;

    @GetMapping
    public ResponseEntity<List<Timesheet>> getAllTimesheets() {
        return ResponseEntity.ok(timesheetService.getAllTimesheets());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Timesheet>> getTimesheetsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(timesheetService.getTimesheetsByUserId(userId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> getTimesheetById(@PathVariable Long id) {
        return ResponseEntity.ok(timesheetService.getTimesheetById(id));
    }
    
    @PostMapping
    public ResponseEntity<Timesheet> createTimesheet(@RequestBody Timesheet timesheet) {
        return ResponseEntity.ok(timesheetService.createTimesheet(timesheet));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Timesheet> updateTimesheet(@PathVariable Long id, @RequestBody Timesheet timesheet) {
        return ResponseEntity.ok(timesheetService.updateTimesheet(id, timesheet));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTimesheet(@PathVariable Long id) {
        timesheetService.deleteTimesheet(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/approve")
    public ResponseEntity<Timesheet> approveTimesheet(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String comments = request.getOrDefault("comments", "");
        return ResponseEntity.ok(timesheetService.approveTimesheet(id, comments));
    }
    
    @PostMapping("/{id}/reject")
    public ResponseEntity<Timesheet> rejectTimesheet(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String comments = request.getOrDefault("comments", "");
        return ResponseEntity.ok(timesheetService.rejectTimesheet(id, comments));
    }
}
package com.ssrmtech.itcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssrmtech.itcompany.model.Timesheet;
import com.ssrmtech.itcompany.model.User;
import com.ssrmtech.itcompany.repository.TimesheetRepository;
import com.ssrmtech.itcompany.repository.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;

    public List<Timesheet> getAllTimesheets() {
        return timesheetRepository.findAll();
    }
    
    public List<Timesheet> getTimesheetsByUserId(Long userId) {
        return timesheetRepository.findByUserId(userId);
    }

    public Timesheet getTimesheetById(Long id) {
        return timesheetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timesheet not found with id: " + id));
    }

    public Timesheet createTimesheet(Timesheet timesheet) {
        // Validate user exists
        User user = userRepository.findById(timesheet.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + timesheet.getUserId()));
        
        // Set status to PENDING
        timesheet.setStatus("PENDING");
        timesheet.setSubmittedDate(new Date());
        
        Timesheet savedTimesheet = timesheetRepository.save(timesheet);
        
        // Send notification to admins
        sendTimesheetSubmissionNotification(savedTimesheet, user);
        
        return savedTimesheet;
    }

    public Timesheet updateTimesheet(Long id, Timesheet timesheetDetails) {
        Timesheet timesheet = getTimesheetById(id);
        
        // Only allow updates if status is PENDING
        if (!timesheet.getStatus().equals("PENDING")) {
            throw new RuntimeException("Cannot update timesheet that has been approved or rejected");
        }
        
        timesheet.setWeekEnding(timesheetDetails.getWeekEnding());
        timesheet.setTotalHours(timesheetDetails.getTotalHours());
        timesheet.setTimesheetHours(timesheetDetails.getTimesheetHours());
        
        return timesheetRepository.save(timesheet);
    }

    public void deleteTimesheet(Long id) {
        Timesheet timesheet = getTimesheetById(id);
        
        // Only allow deletion if status is PENDING
        if (!timesheet.getStatus().equals("PENDING")) {
            throw new RuntimeException("Cannot delete timesheet that has been approved or rejected");
        }
        
        timesheetRepository.delete(timesheet);
    }
    
    public Timesheet approveTimesheet(Long id, String comments) {
        Timesheet timesheet = getTimesheetById(id);
        User reviewer = userRepository.findById(1L) // Assuming reviewer ID is provided
                .orElseThrow(() -> new RuntimeException("Reviewer not found"));
        
        timesheet.setStatus("APPROVED");
        timesheet.setReviewedBy(reviewer.getId());
        timesheet.setReviewedDate(new Date());
        timesheet.setComments(comments);
        
        Timesheet savedTimesheet = timesheetRepository.save(timesheet);
        
        // Send approval notification
        User user = userRepository.findById(timesheet.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        sendTimesheetApprovalNotification(savedTimesheet, user);
        
        return savedTimesheet;
    }
    
    public Timesheet rejectTimesheet(Long id, String comments) {
        Timesheet timesheet = getTimesheetById(id);
        User reviewer = userRepository.findById(1L) // Assuming reviewer ID is provided
                .orElseThrow(() -> new RuntimeException("Reviewer not found"));
        
        timesheet.setStatus("REJECTED");
        timesheet.setReviewedBy(reviewer.getId());
        timesheet.setReviewedDate(new Date());
        timesheet.setComments(comments);
        
        Timesheet savedTimesheet = timesheetRepository.save(timesheet);
        
        // Send rejection notification
        User user = userRepository.findById(timesheet.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        sendTimesheetRejectionNotification(savedTimesheet, user);
        
        return savedTimesheet;
    }
    
    private void sendTimesheetSubmissionNotification(Timesheet timesheet, User user) {
        // Find all admins
        List<User> admins = userRepository.findByRoleIn(List.of("ADMIN", "PARENT_ADMIN"));
        
        for (User admin : admins) {
            // Create email log for timesheet submission notification
            // Implementation in EmailService
        }
    }
    
    private void sendTimesheetApprovalNotification(Timesheet timesheet, User user) {
        // Create email log for timesheet approval notification
        // Implementation in EmailService
    }
    
    private void sendTimesheetRejectionNotification(Timesheet timesheet, User user) {
        // Create email log for timesheet rejection notification
        // Implementation in EmailService
    }
}
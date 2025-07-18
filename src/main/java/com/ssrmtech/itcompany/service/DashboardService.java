package com.ssrmtech.itcompany.service;

import com.ssrmtech.itcompany.dto.DashboardStats;
import com.ssrmtech.itcompany.entity.Role;
import com.ssrmtech.itcompany.entity.Status;
import com.ssrmtech.itcompany.entity.TimesheetStatus;
import com.ssrmtech.itcompany.repository.TimesheetRepository;
import com.ssrmtech.itcompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    public DashboardStats getAdminStats() {
        DashboardStats stats = new DashboardStats();
        
        stats.setTotalUsers(userRepository.countByRole(Role.USER));
        stats.setPendingApprovals(userRepository.countByStatus(Status.PENDING) + 
                                 timesheetRepository.countByStatus(TimesheetStatus.PENDING));
        stats.setMonthlyHours(timesheetRepository.sumApprovedHours());
        stats.setActiveProjects(calculateActiveProjects());
        
        return stats;
    }

    public DashboardStats getUserStats(String userEmail) {
        DashboardStats stats = new DashboardStats();
        
        stats.setTotalSubmitted(timesheetRepository.countByUserEmail(userEmail));
        stats.setCurrentWeekHours(timesheetRepository.getCurrentWeekHours(userEmail));
        stats.setApprovedHours(timesheetRepository.sumApprovedHoursByUser(userEmail).doubleValue());
        stats.setUnreadNotifications(0); // Will be implemented with notification system
        
        return stats;
    }

    private int calculateActiveProjects() {
        // Simple calculation based on user count
        return (int) Math.ceil(userRepository.countByRole(Role.USER) / 3.0);
    }
}
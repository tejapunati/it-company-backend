package com.ssrmtech.itcompany.dto;

public class DashboardStats {
    private long totalUsers;
    private long activeUsers;
    private long pendingApprovals;
    private long totalTimesheets;
    private Double approvedHours;
    private long pendingTimesheets;
    private long emailsSent;
    private Integer monthlyHours;
    private Integer activeProjects;
    private Integer totalSubmitted;
    private Integer currentWeekHours;
    private Integer unreadNotifications;

    // Constructors
    public DashboardStats() {}

    public DashboardStats(long totalUsers, long activeUsers, long pendingApprovals, 
                         long totalTimesheets, Double approvedHours, long pendingTimesheets, long emailsSent) {
        this.totalUsers = totalUsers;
        this.activeUsers = activeUsers;
        this.pendingApprovals = pendingApprovals;
        this.totalTimesheets = totalTimesheets;
        this.approvedHours = approvedHours;
        this.pendingTimesheets = pendingTimesheets;
        this.emailsSent = emailsSent;
    }

    // Getters and Setters
    public long getTotalUsers() { return totalUsers; }
    public void setTotalUsers(long totalUsers) { this.totalUsers = totalUsers; }

    public long getActiveUsers() { return activeUsers; }
    public void setActiveUsers(long activeUsers) { this.activeUsers = activeUsers; }

    public long getPendingApprovals() { return pendingApprovals; }
    public void setPendingApprovals(long pendingApprovals) { this.pendingApprovals = pendingApprovals; }

    public long getTotalTimesheets() { return totalTimesheets; }
    public void setTotalTimesheets(long totalTimesheets) { this.totalTimesheets = totalTimesheets; }

    public Double getApprovedHours() { return approvedHours; }
    public void setApprovedHours(Double approvedHours) { this.approvedHours = approvedHours; }

    public long getPendingTimesheets() { return pendingTimesheets; }
    public void setPendingTimesheets(long pendingTimesheets) { this.pendingTimesheets = pendingTimesheets; }

    public long getEmailsSent() { return emailsSent; }
    public void setEmailsSent(long emailsSent) { this.emailsSent = emailsSent; }

    public Integer getMonthlyHours() { return monthlyHours; }
    public void setMonthlyHours(Integer monthlyHours) { this.monthlyHours = monthlyHours; }

    public Integer getActiveProjects() { return activeProjects; }
    public void setActiveProjects(Integer activeProjects) { this.activeProjects = activeProjects; }

    public Integer getTotalSubmitted() { return totalSubmitted; }
    public void setTotalSubmitted(Integer totalSubmitted) { this.totalSubmitted = totalSubmitted; }

    public Integer getCurrentWeekHours() { return currentWeekHours; }
    public void setCurrentWeekHours(Integer currentWeekHours) { this.currentWeekHours = currentWeekHours; }

    public Integer getUnreadNotifications() { return unreadNotifications; }
    public void setUnreadNotifications(Integer unreadNotifications) { this.unreadNotifications = unreadNotifications; }
}
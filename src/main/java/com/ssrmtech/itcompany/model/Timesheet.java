package com.ssrmtech.itcompany.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "timesheets")
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String weekEnding;
    private String status;
    private Integer totalHours;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date submittedDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewedDate;
    
    private Long reviewedBy;
    private String comments;
    
    @ElementCollection
    @CollectionTable(name = "timesheet_hours", 
        joinColumns = @JoinColumn(name = "timesheet_id"))
    @MapKeyColumn(name = "day")
    @Column(name = "hours")
    private Map<String, Integer> hours;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWeekEnding() {
        return weekEnding;
    }

    public void setWeekEnding(String weekEnding) {
        this.weekEnding = weekEnding;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public Date getReviewedDate() {
        return reviewedDate;
    }

    public void setReviewedDate(Date reviewedDate) {
        this.reviewedDate = reviewedDate;
    }

    public Long getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(Long reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Map<String, Integer> getHours() {
        return hours;
    }

    public void setHours(Map<String, Integer> hours) {
        this.hours = hours;
    }
}
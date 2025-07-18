package com.ssrmtech.itcompany.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "timesheets")
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "week_ending")
    private LocalDate weekEnding;

    @ElementCollection
    @CollectionTable(name = "timesheet_hours", joinColumns = @JoinColumn(name = "timesheet_id"))
    @MapKeyColumn(name = "day_name")
    @Column(name = "hours")
    private Map<String, Double> hours;

    @PositiveOrZero
    @Column(name = "total_hours")
    private Double totalHours = 0.0;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @Enumerated(EnumType.STRING)
    private TimesheetStatus status = TimesheetStatus.PENDING;

    @Column(name = "submitted_date")
    private LocalDateTime submittedDate = LocalDateTime.now();

    @Column(name = "reviewed_date")
    private LocalDateTime reviewedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_by")
    private User reviewedBy;

    // Constructors
    public Timesheet() {}

    public Timesheet(User user, LocalDate weekEnding, Map<String, Double> hours, String comments) {
        this.user = user;
        this.weekEnding = weekEnding;
        this.hours = hours;
        this.comments = comments;
        this.totalHours = hours.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDate getWeekEnding() { return weekEnding; }
    public void setWeekEnding(LocalDate weekEnding) { this.weekEnding = weekEnding; }

    public Map<String, Double> getHours() { return hours; }
    public void setHours(Map<String, Double> hours) { 
        this.hours = hours;
        this.totalHours = hours.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public Double getTotalHours() { return totalHours; }
    public void setTotalHours(Double totalHours) { this.totalHours = totalHours; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public TimesheetStatus getStatus() { return status; }
    public void setStatus(TimesheetStatus status) { this.status = status; }

    public LocalDateTime getSubmittedDate() { return submittedDate; }
    public void setSubmittedDate(LocalDateTime submittedDate) { this.submittedDate = submittedDate; }

    public LocalDateTime getReviewedDate() { return reviewedDate; }
    public void setReviewedDate(LocalDateTime reviewedDate) { this.reviewedDate = reviewedDate; }

    public User getReviewedBy() { return reviewedBy; }
    public void setReviewedBy(User reviewedBy) { this.reviewedBy = reviewedBy; }


}
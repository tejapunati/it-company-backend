package com.ssrmtech.itcompany.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    private String action;

    @NotBlank
    private String description;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(columnDefinition = "TEXT")
    private String metadata;

    // Constructors
    public Activity() {}

    public Activity(User user, String action, String description, ActivityType type) {
        this.user = user;
        this.action = action;
        this.description = description;
        this.type = type;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public ActivityType getType() { return type; }
    public void setType(ActivityType type) { this.type = type; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    public String getMetadata() { return metadata; }
    public void setMetadata(String metadata) { this.metadata = metadata; }

    // Enum
    public enum ActivityType {
        USER, TIMESHEET, APPROVAL, ADMIN
    }
}
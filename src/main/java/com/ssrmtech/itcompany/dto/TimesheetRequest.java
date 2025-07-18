package com.ssrmtech.itcompany.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.util.Map;

public class TimesheetRequest {
    @NotNull
    private LocalDate weekEnding;

    @NotNull
    private Map<String, Double> hours;

    private String comments;

    // Constructors
    public TimesheetRequest() {}

    public TimesheetRequest(LocalDate weekEnding, Map<String, Double> hours, String comments) {
        this.weekEnding = weekEnding;
        this.hours = hours;
        this.comments = comments;
    }

    // Getters and Setters
    public LocalDate getWeekEnding() { return weekEnding; }
    public void setWeekEnding(LocalDate weekEnding) { this.weekEnding = weekEnding; }

    public Map<String, Double> getHours() { return hours; }
    public void setHours(Map<String, Double> hours) { this.hours = hours; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}
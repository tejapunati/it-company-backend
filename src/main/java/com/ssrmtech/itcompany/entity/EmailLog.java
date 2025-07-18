package com.ssrmtech.itcompany.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_logs")
public class EmailLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(name = "to_email")
    private String toEmail;

    @NotBlank
    @Column(name = "from_email")
    private String fromEmail = "noreply@ssrmtech.com";

    @NotBlank
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Enumerated(EnumType.STRING)
    private EmailType type;

    @Enumerated(EnumType.STRING)
    private EmailStatus status = EmailStatus.PENDING;

    @Column(name = "sent_date")
    private LocalDateTime sentDate = LocalDateTime.now();

    @Column(name = "read_date")
    private LocalDateTime readDate;

    @Column(name = "error_message")
    private String errorMessage;

    // Constructors
    public EmailLog() {}

    public EmailLog(String toEmail, String subject, String body, EmailType type) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
        this.type = type;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getToEmail() { return toEmail; }
    public void setToEmail(String toEmail) { this.toEmail = toEmail; }

    public String getFromEmail() { return fromEmail; }
    public void setFromEmail(String fromEmail) { this.fromEmail = fromEmail; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public EmailType getType() { return type; }
    public void setType(EmailType type) { this.type = type; }

    public EmailStatus getStatus() { return status; }
    public void setStatus(EmailStatus status) { this.status = status; }

    public LocalDateTime getSentDate() { return sentDate; }
    public void setSentDate(LocalDateTime sentDate) { this.sentDate = sentDate; }

    public LocalDateTime getReadDate() { return readDate; }
    public void setReadDate(LocalDateTime readDate) { this.readDate = readDate; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }


}
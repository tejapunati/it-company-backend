package com.ssrmtech.itcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssrmtech.itcompany.model.EmailLog;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {
}
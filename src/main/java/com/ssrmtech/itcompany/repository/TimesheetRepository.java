package com.ssrmtech.itcompany.repository;

import com.ssrmtech.itcompany.entity.Timesheet;
import com.ssrmtech.itcompany.entity.TimesheetStatus;
import com.ssrmtech.itcompany.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    
    List<Timesheet> findByUser(User user);
    
    List<Timesheet> findByUserOrderBySubmittedDateDesc(User user);
    
    List<Timesheet> findByStatus(TimesheetStatus status);
    
    List<Timesheet> findByStatusOrderBySubmittedDateDesc(TimesheetStatus status);
    
    Optional<Timesheet> findByUserAndWeekEnding(User user, LocalDate weekEnding);
    
    @Query("SELECT COUNT(t) FROM Timesheet t WHERE t.status = :status")
    long countByStatus(TimesheetStatus status);
    
    @Query("SELECT COALESCE(SUM(t.totalHours), 0) FROM Timesheet t WHERE t.status = 'APPROVED'")
    Integer sumApprovedHours();
    
    @Query("SELECT COALESCE(SUM(t.totalHours), 0) FROM Timesheet t WHERE t.user.email = :email AND t.status = 'APPROVED'")
    Integer sumApprovedHoursByUser(String email);
    
    @Query("SELECT COUNT(t) FROM Timesheet t WHERE t.user.email = :email")
    Integer countByUserEmail(String email);
    
    @Query("SELECT COALESCE(SUM(t.totalHours), 0) FROM Timesheet t WHERE t.user.email = :email AND t.weekEnding >= CURRENT_DATE")
    Integer getCurrentWeekHours(String email);
    
    @Query("SELECT t FROM Timesheet t WHERE t.status = 'PENDING' ORDER BY t.submittedDate DESC")
    List<Timesheet> findPendingTimesheets();
    
    @Query("SELECT t FROM Timesheet t ORDER BY t.submittedDate DESC")
    List<Timesheet> findAllOrderBySubmittedDateDesc();
    
    @Query("SELECT COALESCE(SUM(t.totalHours), 0) FROM Timesheet t WHERE t.status = :status AND t.submittedDate BETWEEN :startDate AND :endDate")
    Double sumTotalHoursByStatusAndSubmittedDateBetween(TimesheetStatus status, LocalDateTime startDate, LocalDateTime endDate);
}
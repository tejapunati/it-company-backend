package com.ssrmtech.itcompany.repository;

import com.ssrmtech.itcompany.entity.Activity;
import com.ssrmtech.itcompany.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    
    List<Activity> findByUserOrderByCreatedDateDesc(User user);
    
    List<Activity> findByTypeOrderByCreatedDateDesc(Activity.ActivityType type);
    
    @Query("SELECT a FROM Activity a ORDER BY a.createdDate DESC")
    List<Activity> findAllOrderByCreatedDateDesc();
    
    List<Activity> findTop10ByOrderByCreatedDateDesc();
    
    List<Activity> findTop20ByOrderByCreatedDateDesc();
}
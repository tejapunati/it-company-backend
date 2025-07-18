package com.ssrmtech.itcompany.service;

import com.ssrmtech.itcompany.entity.Activity;
import com.ssrmtech.itcompany.entity.User;
import com.ssrmtech.itcompany.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public void logActivity(User user, String description) {
        Activity activity = new Activity();
        activity.setUser(user);
        activity.setAction("SYSTEM");
        activity.setDescription(description);
        activity.setCreatedDate(LocalDateTime.now());
        
        activityRepository.save(activity);
    }

    public List<Activity> getRecentActivities() {
        return activityRepository.findTop20ByOrderByCreatedDateDesc();
    }

    public List<Activity> getMyActivities(User user) {
        return activityRepository.findByUserOrderByCreatedDateDesc(user);
    }
}
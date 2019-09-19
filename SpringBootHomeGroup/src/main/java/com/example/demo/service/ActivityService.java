package com.example.demo.service;

import com.example.demo.model.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    Optional<Activity> getActivityById(Long id);
    Activity saveActivity(Activity activity);
    void deleteActivity(Long id);
    List<Activity> findAll();
}

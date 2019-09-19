package com.example.demo.service;

import com.example.demo.model.Prayer;

import java.util.List;
import java.util.Optional;

public interface PrayerService {
    Optional<Prayer> getPrayerById(Long id);
    Prayer savePrayer(Prayer prayer);
    void deletePrayer(Long id);
    List<Prayer> findAll();
}

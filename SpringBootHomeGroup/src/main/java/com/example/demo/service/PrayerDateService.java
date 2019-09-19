package com.example.demo.service;

import com.example.demo.model.PrayerDate;

import java.util.List;
import java.util.Optional;

public interface PrayerDateService {

    Optional<PrayerDate> getPrayerDateById(Long id);
    PrayerDate savePrayerDate(PrayerDate prayerDate);
    void deletePrayerDate(Long id);
    List<PrayerDate> findAll();
}

package com.example.demo.repository;

import com.example.demo.model.PrayerDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrayerDateRepository extends JpaRepository<PrayerDate, Long> {
}

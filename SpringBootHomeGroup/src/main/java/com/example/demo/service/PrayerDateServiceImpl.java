package com.example.demo.service;

import com.example.demo.model.PrayerDate;
import com.example.demo.repository.PrayerDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrayerDateServiceImpl implements PrayerDateService {

    @Autowired
    private PrayerDateRepository prayerDateRepository;

    @Override
    public Optional<PrayerDate> getPrayerDateById(Long id) {
        return prayerDateRepository.findById(id);
    }

    @Override
    public PrayerDate savePrayerDate(PrayerDate prayerDate) {
        prayerDateRepository.save(prayerDate);
        return prayerDate;
    }

    @Override
    public void deletePrayerDate(Long id) {
        prayerDateRepository.deleteById(id);
    }

    @Override
    public List<PrayerDate> findAll() {
        return prayerDateRepository.findAll();
    }
}

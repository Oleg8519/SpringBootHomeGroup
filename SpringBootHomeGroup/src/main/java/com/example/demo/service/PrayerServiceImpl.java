package com.example.demo.service;

import com.example.demo.model.Prayer;
import com.example.demo.repository.PrayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrayerServiceImpl implements PrayerService {

    @Autowired
    private PrayerRepository prayerRepository;

    @Override
    public Optional<Prayer> getPrayerById(Long id) {
        return prayerRepository.findById(id);
    }

    @Override
    public Prayer savePrayer(Prayer prayer) {
        prayerRepository.save(prayer);
        return prayer;
    }

    @Override
    public void deletePrayer(Long id) {
        prayerRepository.deleteById(id);
    }

    @Override
    public List<Prayer> findAll() {
        return prayerRepository.findAll();
    }
}

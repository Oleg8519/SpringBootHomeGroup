package com.example.demo.service;

import com.example.demo.model.HomeGroupDate;
import com.example.demo.repository.HomeGroupDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeGroupDateServiceImpl implements HomeGroupDateService {

    @Autowired
    private HomeGroupDateRepository homeGroupDateRepository;

    @Override
    public Optional<HomeGroupDate> getHomeGroupDateById(Long id) {
        return homeGroupDateRepository.findById(id);
    }

    @Override
    public HomeGroupDate saveHomeGroupDate(HomeGroupDate homeGroupDate) {
        homeGroupDateRepository.save(homeGroupDate);
        return homeGroupDate;
    }

    @Override
    public void deleteHomeGroupDate(Long id) {
        homeGroupDateRepository.deleteById(id);
    }

    @Override
    public List<HomeGroupDate> findAll() {
        return homeGroupDateRepository.findAll();
    }
}

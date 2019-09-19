package com.example.demo.service;

import com.example.demo.model.HomeGroupDate;

import java.util.List;
import java.util.Optional;

public interface HomeGroupDateService {

    Optional<HomeGroupDate> getHomeGroupDateById(Long id);
    HomeGroupDate saveHomeGroupDate(HomeGroupDate homeGroupDate);
    void deleteHomeGroupDate(Long id);
    List<HomeGroupDate> findAll();
}

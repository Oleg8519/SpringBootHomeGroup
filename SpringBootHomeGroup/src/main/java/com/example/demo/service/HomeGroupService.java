package com.example.demo.service;

import com.example.demo.model.HomeGroup;

import java.util.List;
import java.util.Optional;

public interface HomeGroupService {
    Optional<HomeGroup> getHomeGroupById(Long id);
    HomeGroup saveHomeGroup(HomeGroup homeGroup);
    void deleteHomeGroup(Long id);
    List<HomeGroup> findAll();
}

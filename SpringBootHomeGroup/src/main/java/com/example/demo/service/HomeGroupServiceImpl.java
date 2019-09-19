package com.example.demo.service;

import com.example.demo.model.HomeGroup;
import com.example.demo.repository.HomeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeGroupServiceImpl implements HomeGroupService{

    @Autowired
    private HomeGroupRepository homeGroupRepository;

    @Override
    public Optional<HomeGroup> getHomeGroupById(Long id) {
        return homeGroupRepository.findById(id);
    }

    @Override
    public HomeGroup saveHomeGroup(HomeGroup homeGroup) {
        homeGroupRepository.save(homeGroup);
        return homeGroup;
    }

    @Override
    public void deleteHomeGroup(Long id) {
        homeGroupRepository.deleteById(id);
    }

    @Override
    public List<HomeGroup> findAll() {
        return homeGroupRepository.findAll();
    }
}

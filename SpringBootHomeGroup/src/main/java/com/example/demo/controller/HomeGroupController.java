package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HomeGroup;
import com.example.demo.model.HomeGroupDate;
import com.example.demo.model.User;
import com.example.demo.service.HomeGroupService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class HomeGroupController {

    @Autowired
    private HomeGroupService homeGroupService;
    @Autowired
    private UserService userService;

    // регистрация HomeGroup и User
    @PostMapping("/homeGroups/{homeGroupId}/users")
    public User createUser(@PathVariable (value = "homeGroupId") Long homeGroupId,
                          @Valid @RequestBody User user) {
        return homeGroupService.getHomeGroupById(homeGroupId).map(homeGroup -> {
            user.setHomeGroup(homeGroup);
            return userService.saveUser(user);

        }).orElseThrow(() -> new ResourceNotFoundException("homeGroupId " + homeGroupId + " not found"));
    }

    // регистрация HomeGroup
    @PostMapping("/homeGroups")
    public HomeGroup createHomeGroup(@Valid @RequestBody HomeGroup homeGroup) {
        return homeGroupService.saveHomeGroup(homeGroup);
    }

    //удаление HomeGroup
    @DeleteMapping("/homeGroups/{id}")
    public void delete(@PathVariable Long id) {
        homeGroupService.deleteHomeGroup(id);
    }

    //вывод всех HomeGroup
    @GetMapping("/homeGroups")
    public List getAllHomeGroups() {
        return homeGroupService.findAll();
    }

    //вывод HomeGroup по id
    @GetMapping("/homeGroups/{id}")
    public Optional<HomeGroup> getAllHomeGroupById(@PathVariable Long id){
        return homeGroupService.getHomeGroupById(id);
    }
    //=================================================================================


    //получить все homeGroupData для homeGroup
    @GetMapping("homeGroups/{homeGroupId}/homeGroupDates")
    public Set<HomeGroupDate> getHomeGroupDates(@PathVariable Long homeGroupId){

        return this.homeGroupService.getHomeGroupById(homeGroupId).map((homeGroup) -> {
            return homeGroup.getHomeGroupDateSet();
        }).orElseThrow(() -> new ResourceNotFoundException("HomeGroup", homeGroupId));
    }
    //===================================================================================
}

package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HomeGroup;
import com.example.demo.model.HomeGroupDate;
import com.example.demo.model.Prayer;
import com.example.demo.model.User;
import com.example.demo.service.HomeGroupDateService;
import com.example.demo.service.HomeGroupService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class HomeGroupDateController {

    @Autowired
    private HomeGroupDateService homeGroupDateService;

    @Autowired
    private HomeGroupService homeGroupService;

    @Autowired
    private UserService userService;
    //===============================================================================================

    // регистрация HomeGroupDate
    @PostMapping("/homeGroupDates")
    public HomeGroupDate createHomeGroupDate(@Valid @RequestBody HomeGroupDate homeGroupDate) {
        return homeGroupDateService.saveHomeGroupDate(homeGroupDate);
    }
    //===============================================================================================

    //удаление HomeGroupDate
    @DeleteMapping("/homeGroupDates/{id}")
    public void delete(@PathVariable Long id) {
        homeGroupDateService.deleteHomeGroupDate(id);
    }
    //================================================================================================

    //вывод всех HomeGroupDate
    @GetMapping("/HomeGroupDates")
    public List getAllHomeGroupDates() {
        return homeGroupDateService.findAll();
    }
    //================================================================================================

    //вывод HomeGroupDate по id
    @GetMapping("/homeGroupDates/{id}")
    public Optional<HomeGroupDate> getAllHomeGroupDateById(@PathVariable Long id) {
        return homeGroupDateService.getHomeGroupDateById(id);

    }
    //===============================================================================================

    //связь id homeGroupDate c id user
    @PostMapping("homeGroupDates/{id}/users/{userId}")
    public Set<User> addUser(@PathVariable Long id, @PathVariable Long userId) {

        User user = this.userService.getUserById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", userId)
        );
        return this.homeGroupDateService.getHomeGroupDateById(id).map((homeGroupDate) -> {
            homeGroupDate.getUserSet().add(user);
            return this.homeGroupDateService.saveHomeGroupDate(homeGroupDate).getUserSet();
        }).orElseThrow(() -> new ResourceNotFoundException("HomeGroupDate", id));

    }
    //================================================================================================

    //связь id homeGroupDate c id homeGroup
    @PostMapping("homeGroupDates/{id}/homeGroups/{homeGroupId}")
    public Set<HomeGroup> addHomeGroup(@PathVariable Long id, @PathVariable Long homeGroupId) {

        HomeGroup homeGroup = this.homeGroupService.getHomeGroupById(homeGroupId).orElseThrow(
                () -> new ResourceNotFoundException("HomeGroup", homeGroupId)
        );
        return this.homeGroupDateService.getHomeGroupDateById(id).map((homeGroupDate) -> {
            homeGroupDate.getHomeGroupSet().add(homeGroup);
            return this.homeGroupDateService.saveHomeGroupDate(homeGroupDate).getHomeGroupSet();
        }).orElseThrow(() -> new ResourceNotFoundException("HomeGroupDate", id));

    }
    //================================================================================================

    // получить всех users по homeGroupDate_id
    @GetMapping("homeGroupDates/{id}/users")
    public Set<User> getUsers(@PathVariable Long id){

        return this.homeGroupDateService.getHomeGroupDateById(id).map((homeGroupDate) -> {
            return homeGroupDate.getUserSet();
        }).orElseThrow(() -> new ResourceNotFoundException("HomeGroupDate", id));
    }
    //==================================================================================================

    // получить все homeGroup по homeGroupDate_id
    @GetMapping("homeGroupDates/{id}/homeGroups")
    public Set<HomeGroup> getHomeGroups(@PathVariable Long id){

        return this.homeGroupDateService.getHomeGroupDateById(id).map((homeGroupDate) -> {
            return homeGroupDate.getHomeGroupSet();
        }).orElseThrow(() -> new ResourceNotFoundException("homeGroupDate", id));
    }
}
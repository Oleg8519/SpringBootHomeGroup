package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private PrayerService prayerService;
    @Autowired
    private HomeGroupDateService homeGroupDateService;
    @Autowired
    private PrayerDateService prayerDateService;

    // регистрация user
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    //удаление user
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    //вывод всех user
    @GetMapping("/users")
    public List getAllUsers() {
        return userService.findAll();
    }

    //вывод user по id
    @GetMapping("/users/{id}")
    public Optional<User> getAllUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    //================================================================================

    //связь id user c id activity
    @PostMapping("users/{id}/activitys/{activityId}")
    public Set<Activity> addActivity(@PathVariable Long id, @PathVariable Long activityId){

        Activity activity = this.activityService.getActivityById(activityId).orElseThrow(
                () -> new ResourceNotFoundException("Activity", activityId)
        );
        return this.userService.getUserById(id).map((user) -> {
            user.getActivitySet().add(activity);
            return this.userService.saveUser(user).getActivitySet();
        }).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }
    //==================================================================================

    //связь id user c id homeGroupDate
    @PostMapping("users/{id}/homeGroupDates/{homeGroupDateId}")
    public Set<HomeGroupDate> addHomeGroupDate(@PathVariable Long id, @PathVariable Long homeGroupDateId){

        HomeGroupDate homeGroupDate = this.homeGroupDateService.getHomeGroupDateById(homeGroupDateId).orElseThrow(
                () -> new ResourceNotFoundException("HomeGroupDate", homeGroupDateId)
        );
        return this.userService.getUserById(id).map((user) -> {
            user.getHomeGroupDateSet().add(homeGroupDate);
            return this.userService.saveUser(user).getHomeGroupDateSet();
        }).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }
    //==================================================================================

    //связь id user c id prayer
    @PostMapping("users/{id}/prayers/{prayerId}")
    public Set<Prayer> addPrayer(@PathVariable Long id, @PathVariable Long prayerId){

        Prayer prayer = this.prayerService.getPrayerById(prayerId).orElseThrow(
                () -> new ResourceNotFoundException("Prayer", prayerId)
        );
        return this.userService.getUserById(id).map((user) -> {
            user.getPrayerSet().add(prayer);
            return this.userService.saveUser(user).getPrayerSet();
        }).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }
    //==================================================================================

    //связь id user c id prayerDate
    @PostMapping("users/{id}/prayerDate/{prayerDateId}")
    public Set<PrayerDate> addPrayerDate(@PathVariable Long id, @PathVariable Long prayerDateId){

        PrayerDate prayerDate = this.prayerDateService.getPrayerDateById(prayerDateId).orElseThrow(
                () -> new ResourceNotFoundException("PrayerDate", prayerDateId)
        );
        return this.userService.getUserById(id).map((user) -> {
            user.getPrayerDateSet().add(prayerDate);
            return this.userService.saveUser(user).getPrayerDateSet();
        }).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    //получить все homeGroupDate для user-a
    @GetMapping("users/{userId}/homeGroupDates")
    public Set<HomeGroupDate> getHomeGroupDates(@PathVariable Long userId){

        return this.userService.getUserById(userId).map((user) -> {
            return user.getHomeGroupDateSet();
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    //получить все prayer_date для user
    @GetMapping("users/{userId}/prayerDates")
    public Set<PrayerDate> getPrayerDates(@PathVariable Long userId){

        return this.userService.getUserById(userId).map((user) -> {
            return user.getPrayerDateSet();
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

}

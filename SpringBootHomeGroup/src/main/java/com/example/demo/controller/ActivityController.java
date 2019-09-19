package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Activity;
import com.example.demo.model.User;
import com.example.demo.service.ActivityService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    //==============================================================================================

    // регистрация Activity
    @PostMapping("/activitys")
    public Activity createActivity(@Valid @RequestBody Activity activity){
        return activityService.saveActivity(activity);
    }
    //===============================================================================================

    //удаление Activity
    @DeleteMapping("/activitys/{id}")
    public void delete(@PathVariable Long id) {
        activityService.deleteActivity(id);
    }
    //===============================================================================================

    //вывод всех Activity
    @GetMapping("/activitys")
    public List getAllActivitys() {
        return activityService.findAll();
    }
    //===============================================================================================

    //вывод Activity по id
    @GetMapping("/activitys/{id}")
    public Optional<Activity> getAllActivityById(@PathVariable Long id){
        return activityService.getActivityById(id);
    }
    //===============================================================================================

    //связь id activity c id user
    @PostMapping("activitys/{id}/users/{userId}")
    public Set<User> addUser(@PathVariable Long id, @PathVariable Long userId){

        User user = this.userService.getUserById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", userId)
        );
        return this.activityService.getActivityById(id).map((activity) -> {
            activity.getUserSet().add(user);
            return this.activityService.saveActivity(activity).getUserSet();
        }).orElseThrow(() -> new ResourceNotFoundException("Activity", id));
    }
    //==================================================================================================

}

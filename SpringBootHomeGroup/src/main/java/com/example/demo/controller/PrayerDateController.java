package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Activity;
import com.example.demo.model.Prayer;
import com.example.demo.model.PrayerDate;
import com.example.demo.model.User;
import com.example.demo.service.PrayerDateService;
import com.example.demo.service.PrayerService;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class PrayerDateController {

    @Autowired
    private PrayerDateService prayerDateService;

    @Autowired
    private UserService userService;

    @Autowired
    private PrayerService prayerService;
    //==============================================================================================

    // регистрация PrayerDate
    @PostMapping("/prayerDates")
    public PrayerDate createPrayerDate(@Valid @RequestBody PrayerDate prayerDate){
        return prayerDateService.savePrayerDate(prayerDate);
    }
    //=============================================================================================

    //удаление PrayerDate
    @DeleteMapping("/prayerDate/{id}")
    public void delete(@PathVariable Long id) {
        prayerDateService.deletePrayerDate(id);
    }
    //==============================================================================================

    //вывод всех PrayerDate
    @GetMapping("/prayerDates")
    public List getAllPrayerDates() {
        return prayerDateService.findAll();
    }
    //==============================================================================================

    //вывод PrayerDate по id
    @GetMapping("/prayerDates/{id}")
    public Optional<PrayerDate> getAllPrayerDateById(@PathVariable Long id){
        return prayerDateService.getPrayerDateById(id);

    }
    //===============================================================================================

    //связь id prayerDate c id user
    @PostMapping("prayerDates/{id}/users/{userId}")
    public Set<User> addUser(@PathVariable Long id, @PathVariable Long userId){

        User user = this.userService.getUserById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", userId)
        );
        return this.prayerDateService.getPrayerDateById(id).map((prayerDate) -> {
            prayerDate.getUserSet().add(user);
            return this.prayerDateService.savePrayerDate(prayerDate).getUserSet();
        }).orElseThrow(() -> new ResourceNotFoundException("PrayerDate", id));
    }
    //================================================================================================

    //связь id prayerDate c id prayer
    @PostMapping("prayerDates/{id}/prayers/{prayerId}")
    public Set<Prayer> addPrayer(@PathVariable Long id, @PathVariable Long prayerId){

        Prayer prayer = this.prayerService.getPrayerById(prayerId).orElseThrow(
                () -> new ResourceNotFoundException("Prayer", prayerId)
        );
        return this.prayerDateService.getPrayerDateById(id).map((prayerDate) -> {
            prayerDate.getPrayerSet().add(prayer);
            return this.prayerDateService.savePrayerDate(prayerDate).getPrayerSet();
        }).orElseThrow(() -> new ResourceNotFoundException("PrayerDate", id));
    }
    //=================================================================================================

    // получить всех users по prayerDate_id
    @GetMapping("prayerDates/{id}/users")
    public Set<User> getUsers(@PathVariable Long id){

        return this.prayerDateService.getPrayerDateById(id).map((prayerDate) -> {
            return prayerDate.getUserSet();
        }).orElseThrow(() -> new ResourceNotFoundException("PrayerDate", id));
    }
    //==================================================================================================

    // получить все prayer по prayerDate_id
    @GetMapping("prayerDates/{id}/prayers")
    public Set<Prayer> getPrayers(@PathVariable Long id){

        return this.prayerDateService.getPrayerDateById(id).map((prayerDate) -> {
            return prayerDate.getPrayerSet();
        }).orElseThrow(() -> new ResourceNotFoundException("PrayerDate", id));
    }
}

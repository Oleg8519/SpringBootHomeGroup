package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Prayer;
import com.example.demo.model.PrayerDate;
import com.example.demo.model.User;
import com.example.demo.service.PrayerService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class PrayerController {
    @Autowired
    private PrayerService prayerService;
    @Autowired
    private UserService userService;

    // регистрация prayer
    @PostMapping("/prayers")
    public Prayer createPrayer(@Valid @RequestBody Prayer prayer) {
        return prayerService.savePrayer(prayer);
    }

    //удаление prayer
    @DeleteMapping("/prayers/{id}")
    public void delete(@PathVariable Long id) {
        prayerService.deletePrayer(id);
    }

    //вывод всех prayer
    @GetMapping("/prayers")
    public List getAllPrayers() {
        return prayerService.findAll();
    }

    //вывод prayer по id
    @GetMapping("/prayers/{id}")
    public Optional<Prayer> getAllPrayerById(@PathVariable Long id){
        return prayerService.getPrayerById(id);
    }
    //=========================================================================================================

    //связь id prayer c id user
    @PostMapping("prayers/{id}/users/{userId}")
    public Set<User> addUser(@PathVariable Long id, @PathVariable Long userId){

        User user = this.userService.getUserById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", userId)
        );
        return this.prayerService.getPrayerById(id).map((prayer) -> {
            prayer.getUserSet().add(user);
            return this.prayerService.savePrayer(prayer).getUserSet();
        }).orElseThrow(() -> new ResourceNotFoundException("Prayer", id));
    }
    //===========================================================================================

    //получить все prayerDate для prayer
    @GetMapping("prayers/{prayerId}/prayerDates")
    public Set<PrayerDate> getPrayerDates(@PathVariable Long prayerId){

        return this.prayerService.getPrayerById(prayerId).map((prayer) -> {
            return prayer.getPrayerDateSet();
        }).orElseThrow(() -> new ResourceNotFoundException("Prayer", prayerId));
    }
    //===========================================================================================
}

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "phoneNumber")
    private Long phoneNumber;

    @Column(name = "email")
    private String email;

    //========================================================
    //связь с таблицей home_group
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "homeGroup_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private HomeGroup homeGroup;

    //========================================================
    //связь с таблицей activity
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "user_activity",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "activity_id")}

    )

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Activity> activitySet = new HashSet<>();

    //=========================================================
    //связь с таблицей prayer
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "user_prayer",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "prayer_id")}

    )

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Prayer> prayerSet = new HashSet<>();

    //=========================================================
    //связь с таблицей homeGroupDate
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "user_homeGroupDate",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "homeGroupDate_id")}

    )

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<HomeGroupDate> homeGroupDateSet = new HashSet<>();

    //=========================================================
    //связь с таблицей prayerDate
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "user_prayerDate",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "prayerDate_id")}

    )

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<PrayerDate> prayerDateSet = new HashSet<>();


    //=======================================================
    //конструкторы
    public User() {
    }

    public User(String name, String dateOfBirth, Long phoneNumber, String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User(String name, String dateOfBirth, Long phoneNumber, String email, HomeGroup homeGroup) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeGroup = homeGroup;
    }

    public User(String name, String dateOfBirth, Long phoneNumber, String email, HomeGroup homeGroup, Set<Activity> activitySet) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeGroup = homeGroup;
        this.activitySet = activitySet;
    }

    public User(String name, String dateOfBirth, Long phoneNumber, String email, HomeGroup homeGroup, Set<Activity> activitySet, Set<Prayer> prayerSet) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeGroup = homeGroup;
        this.activitySet = activitySet;
        this.prayerSet = prayerSet;
    }

    public User(String name, String dateOfBirth, Long phoneNumber, String email, HomeGroup homeGroup, Set<Activity> activitySet, Set<Prayer> prayerSet, Set<HomeGroupDate> homeGroupDateSet) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeGroup = homeGroup;
        this.activitySet = activitySet;
        this.prayerSet = prayerSet;
        this.homeGroupDateSet = homeGroupDateSet;
    }

    public User(String name, String dateOfBirth, Long phoneNumber, String email, HomeGroup homeGroup, Set<Activity> activitySet, Set<Prayer> prayerSet, Set<HomeGroupDate> homeGroupDateSet, Set<PrayerDate> prayerDateSet) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeGroup = homeGroup;
        this.activitySet = activitySet;
        this.prayerSet = prayerSet;
        this.homeGroupDateSet = homeGroupDateSet;
        this.prayerDateSet = prayerDateSet;
    }

    //геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HomeGroup getHomeGroup() {
        return homeGroup;
    }

    public void setHomeGroup(HomeGroup homeGroup) {
        this.homeGroup = homeGroup;
    }

    public Set<Activity> getActivitySet() {
        return activitySet;
    }

    public void setActivitySet(Set<Activity> activitySet) {
        this.activitySet = activitySet;
    }

    public Set<Prayer> getPrayerSet() {
        return prayerSet;
    }

    public void setPrayerSet(Set<Prayer> prayerSet) {
        this.prayerSet = prayerSet;
    }

    public Set<HomeGroupDate> getHomeGroupDateSet() {
        return homeGroupDateSet;
    }

    public void setHomeGroupDateSet(Set<HomeGroupDate> homeGroupDateSet) {
        this.homeGroupDateSet = homeGroupDateSet;
    }

    public Set<PrayerDate> getPrayerDateSet() {
        return prayerDateSet;
    }

    public void setPrayerDateSet(Set<PrayerDate> prayerDateSet) {
        this.prayerDateSet = prayerDateSet;
    }

    //метод toString


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", homeGroup=" + homeGroup +
                ", activitySet=" + activitySet +
                ", prayerSet=" + prayerSet +
                ", homeGroupDateSet=" + homeGroupDateSet +
                ", prayerDateSet=" + prayerDateSet +
                '}';
    }
}

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prayerDate")

public class PrayerDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;
    //===========================================================

    //связь с таблицей user
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "user_prayerDate",
            joinColumns = {@JoinColumn(name = "prayerDate_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<User> userSet = new HashSet<>();
    //=============================================================

    //связь с таблицей prayer
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "prayer_prayerDate",
            joinColumns = {@JoinColumn(name = "prayerDate_id")},
            inverseJoinColumns = {@JoinColumn(name = "prayer_id")}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Prayer> prayerSet = new HashSet<>();
    //=============================================================

    //конструкторы

    public PrayerDate() {
    }

    public PrayerDate(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public PrayerDate(String date, String time, Set<User> userSet) {
        this.date = date;
        this.time = time;
        this.userSet = userSet;
    }

    public PrayerDate(String date, String time, Set<User> userSet, Set<Prayer> prayerSet) {
        this.date = date;
        this.time = time;
        this.userSet = userSet;
        this.prayerSet = prayerSet;
    }

    //===========================================================

    //геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<Prayer> getPrayerSet() {
        return prayerSet;
    }

    public void setPrayerSet(Set<Prayer> prayerSet) {
        this.prayerSet = prayerSet;
    }

    //===========================================================

    //метод toString
    @Override
    public String toString() {
        return "PrayerDate{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", userSet=" + userSet +
                ", prayerSet=" + prayerSet +
                '}';
    }
}

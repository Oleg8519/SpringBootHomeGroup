package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "homeGroupDate")

public class HomeGroupDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;
    //========================================================

    //связь с таблицей user
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "user_homeGroupDate",
            joinColumns = {@JoinColumn(name = "homeGroupDate_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<User> userSet = new HashSet<>();
    //=========================================================

    //связь с таблицей homeGroup
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "homeGroup_homeGroupDate",
            joinColumns = {@JoinColumn(name = "homeGroupDate_id")},
            inverseJoinColumns = {@JoinColumn(name = "homeGroup_id")}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<HomeGroup> homeGroupSet = new HashSet<>();
    //=========================================================

    // конструкторы
    public HomeGroupDate() {
    }

    public HomeGroupDate(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public HomeGroupDate(String date, String time, Set<User> userSet) {
        this.date = date;
        this.time = time;
        this.userSet = userSet;
    }

    public HomeGroupDate(String date, String time, Set<User> userSet, Set<HomeGroup> homeGroupSet) {
        this.date = date;
        this.time = time;
        this.userSet = userSet;
        this.homeGroupSet = homeGroupSet;
    }

    //=========================================================

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

    public Set<HomeGroup> getHomeGroupSet() {
        return homeGroupSet;
    }

    public void setHomeGroupSet(Set<HomeGroup> homeGroupSet) {
        this.homeGroupSet = homeGroupSet;
    }

    //============================================================

    // метод to String

    @Override
    public String toString() {
        return "HomeGroupDate{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", userSet=" + userSet +
                ", homeGroupSet=" + homeGroupSet +
                '}';
    }
}

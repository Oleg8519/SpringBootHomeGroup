package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prayer")

public class Prayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titlePrayer")
    private String titlePrayer;

    @Column(name = "nameOfLeader")
    private String nameOfLeader;
    //==========================================================

    //связь с таблицей user
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "user_prayer",
            joinColumns = {@JoinColumn(name = "prayer_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
            )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<User> userSet = new HashSet<>();
    //=========================================================

    //связь с таблицей prayerDate
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "prayer_prayerDate",
            joinColumns = {@JoinColumn(name = "prayer_id")},
            inverseJoinColumns = {@JoinColumn(name = "prayerDate_id")}

    )

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<PrayerDate> prayerDateSet = new HashSet<>();
    //==============================================================

    //конструкторы
    public Prayer() {
    }

    public Prayer(String titlePrayer, String nameOfLeader) {
        this.titlePrayer = titlePrayer;
        this.nameOfLeader = nameOfLeader;
    }

    public Prayer(String titlePrayer, String nameOfLeader, Set<User> userSet) {
        this.titlePrayer = titlePrayer;
        this.nameOfLeader = nameOfLeader;
        this.userSet = userSet;
    }

    public Prayer(String titlePrayer, String nameOfLeader, Set<User> userSet, Set<PrayerDate> prayerDateSet) {
        this.titlePrayer = titlePrayer;
        this.nameOfLeader = nameOfLeader;
        this.userSet = userSet;
        this.prayerDateSet = prayerDateSet;
    }

    //===========================================================

    //геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitlePrayer() {
        return titlePrayer;
    }

    public void setTitlePrayer(String titlePrayer) {
        this.titlePrayer = titlePrayer;
    }

    public String getNameOfLeader() {
        return nameOfLeader;
    }

    public void setNameOfLeader(String nameOfLeader) {
        this.nameOfLeader = nameOfLeader;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<PrayerDate> getPrayerDateSet() {
        return prayerDateSet;
    }

    public void setPrayerDateSet(Set<PrayerDate> prayerDateSet) {
        this.prayerDateSet = prayerDateSet;
    }

    //===================================================

    // метод toString

    @Override
    public String toString() {
        return "Prayer{" +
                "id=" + id +
                ", titlePrayer='" + titlePrayer + '\'' +
                ", nameOfLeader='" + nameOfLeader + '\'' +
                ", userSet=" + userSet +
                ", prayerDateSet=" + prayerDateSet +
                '}';
    }
}

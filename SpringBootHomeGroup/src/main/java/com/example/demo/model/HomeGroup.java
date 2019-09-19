package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "homeGroup")

public class HomeGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "adress")
    private String adress;

    @Column(name = "nameOfLeader")
    private String nameOfLeader;
    //===========================================================

    //связь с таблицей user
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "homeGroup")
    private Set<User> userSet = new HashSet<>();
    //===========================================================

    //связь с таблицей homeGroupDate
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "homeGroup_homeGroupDate",
            joinColumns = {@JoinColumn(name = "homeGroup_id")},
            inverseJoinColumns = {@JoinColumn(name = "homeGroupDate_id")}

    )

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<HomeGroupDate> homeGroupDateSet = new HashSet<>();

    //конструкторы
    public HomeGroup() {
    }

    public HomeGroup(String adress, String nameOfLeader) {
        this.adress = adress;
        this.nameOfLeader = nameOfLeader;
    }

    public HomeGroup(String adress, String nameOfLeader, Set<User> userSet) {
        this.adress = adress;
        this.nameOfLeader = nameOfLeader;
        this.userSet = userSet;
    }

    public HomeGroup(String adress, String nameOfLeader, Set<User> userSet, Set<HomeGroupDate> homeGroupDateSet) {
        this.adress = adress;
        this.nameOfLeader = nameOfLeader;
        this.userSet = userSet;
        this.homeGroupDateSet = homeGroupDateSet;
    }

    //======================================================================

    //геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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

    public Set<HomeGroupDate> getHomeGroupDateSet() {
        return homeGroupDateSet;
    }

    public void setHomeGroupDateSet(Set<HomeGroupDate> homeGroupDateSet) {
        this.homeGroupDateSet = homeGroupDateSet;
    }
    //================================================================

    //метод toString

    @Override
    public String toString() {
        return "HomeGroup{" +
                "id=" + id +
                ", adress='" + adress + '\'' +
                ", nameOfLeader='" + nameOfLeader + '\'' +
                ", userSet=" + userSet +
                ", homeGroupDateSet=" + homeGroupDateSet +
                '}';
    }
}

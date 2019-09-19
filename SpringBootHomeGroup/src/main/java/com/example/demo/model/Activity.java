package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "activity")

public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "favoriteActivity")
    private String favoriteActivity;

    @Column(name = "nameOfLeader")
    private String nameOfLeader;
    //==========================================================

    //связь с таблицей user
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "user_activity",
            joinColumns = {@JoinColumn(name = "activity_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<User> userSet = new HashSet<>();
    //=================================================================

    // конструкторы
    public Activity() {
    }

    public Activity(String favoriteActivity, String nameOfLeader) {
        this.favoriteActivity = favoriteActivity;
        this.nameOfLeader = nameOfLeader;
    }

    public Activity(String favoriteActivity, String nameOfLeader, Set<User> userSet) {
        this.favoriteActivity = favoriteActivity;
        this.nameOfLeader = nameOfLeader;
        this.userSet = userSet;
    }
    //=================================================================

    //геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFavoriteActivity() {
        return favoriteActivity;
    }

    public void setFavoriteActivity(String favoriteActivity) {
        this.favoriteActivity = favoriteActivity;
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
    //========================================================================

    // метод toString
    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", favoriteActivity='" + favoriteActivity + '\'' +
                ", nameOfLeader='" + nameOfLeader + '\'' +
                ", userSet=" + userSet +
                '}';
    }
}

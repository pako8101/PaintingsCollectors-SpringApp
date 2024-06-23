package com.paintingscollectors.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;
@OneToMany(mappedBy = "owner",fetch = FetchType.EAGER)
    private List<Painting> addedPaintings;
@ManyToMany(fetch = FetchType.EAGER)
private Set<Painting> favoritePaintings;

@ManyToMany(fetch = FetchType.EAGER)
private Set<Painting> ratedPaintings;


    public User() {
        this.addedPaintings = new ArrayList<>();
        this.favoritePaintings = new HashSet<>();
        this.ratedPaintings = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Painting> getAddedPaintings() {
        return addedPaintings;
    }

    public User setAddedPaintings(List<Painting> addedPaintings) {
        this.addedPaintings = addedPaintings;
        return this;
    }

    public Set<Painting> getFavoritePaintings() {
        return favoritePaintings;
    }

    public User setFavoritePaintings(Set<Painting> favoritePaintings) {
        this.favoritePaintings = favoritePaintings;
        return this;
    }

    public Set<Painting> getRatedPaintings() {
        return ratedPaintings;
    }

    public User setRatedPaintings(Set<Painting> ratedPaintings) {
        this.ratedPaintings = ratedPaintings;
        return this;
    }
    public void addFavourite(Painting painting) {
        this.favoritePaintings.add(painting);
    }
}

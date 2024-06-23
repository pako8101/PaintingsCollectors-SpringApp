package com.paintingscollectors.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "paintings")
public class Painting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String author;

    @ManyToOne(optional = false)
    private Style style;

    @ManyToOne(optional = false)
    private User owner;
@Column(nullable = false)
    private String imgUrl;
@Column(nullable = false)
private boolean isFavourite;

    @Column(nullable = false)
    private int votes;

    public Painting() {
    }

    public long getId() {
        return id;
    }

    public Painting setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Painting setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Painting setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Painting setStyle(Style style) {
        this.style = style;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Painting setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Painting setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public Painting setFavourite(boolean favourite) {
        isFavourite = favourite;
        return this;
    }

    public int getVotes() {
        return votes;
    }

    public Painting setVotes(int votes) {
        this.votes = votes;
        return this;
    }
}

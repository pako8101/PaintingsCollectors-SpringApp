package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.model.entity.enums.StyleName;

public class PaintingInfoDto {
private long id;
    private String imageUrl;
    private String name;
    private String author;
    private StyleName style;
    private User owner;

    public PaintingInfoDto(Painting painting) {
        this.imageUrl = painting.getImgUrl();
        this.name = painting.getName();
        this.author = painting.getAuthor();
        this.style = painting.getStyle().getStyleName();
        this.id = painting.getId();
        this.owner = painting.getOwner();
    }

    public long getId() {
        return id;
    }

    public PaintingInfoDto setId(long id) {
        this.id = id;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public PaintingInfoDto setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PaintingInfoDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public PaintingInfoDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public PaintingInfoDto setAuthor(String author) {
        this.author = author;
        return this;
    }

    public StyleName getStyle() {
        return style;
    }

    public PaintingInfoDto setStyle(StyleName style) {
        this.style = style;
        return this;
    }
}

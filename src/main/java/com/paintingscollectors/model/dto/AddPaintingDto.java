package com.paintingscollectors.model.dto;
import com.paintingscollectors.model.entity.enums.StyleName;

import javax.validation.constraints.*;
public class AddPaintingDto {


    @NotNull
    @Size(min = 4, max = 40,message = "Name length must be between 4 and 40 symbols!")
    private String name;
    @NotNull
    @Size(min = 5, max = 30,message = "Author name length must be between 4 and 40 symbols!")
    private String author;
    @NotNull
    @Size(min = 1,max = 150,message = "Valid url must be not more then 150 symbols!")
    private String imageUrl;
    @NotNull(message = "You have to select style!")
    private StyleName style;

    public AddPaintingDto() {
    }

    public String getName() {
        return name;
    }

    public AddPaintingDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public AddPaintingDto setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddPaintingDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public StyleName getStyle() {
        return style;
    }

    public AddPaintingDto setStyle(StyleName style) {
        this.style = style;
        return this;
    }
}

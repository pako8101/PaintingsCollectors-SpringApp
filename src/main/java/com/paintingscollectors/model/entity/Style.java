package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.entity.enums.StyleName;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
@Enumerated(EnumType.STRING)
@Column(nullable = false,unique = true)
    private StyleName styleName;
    @Column(nullable = false)
    private String description;

    public Style(StyleName name, String description) {
        super();
        this.styleName = name;
        this.description = description;

    }

    public Style() {
    }

    public long getId() {
        return id;
    }

    public Style setId(long id) {
        this.id = id;
        return this;
    }

    public StyleName getStyleName() {
        return styleName;
    }

    public Style setStyleName(StyleName styleName) {
        this.styleName = styleName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }
}

package com.bortn.ddutBlog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "short_news")
public class ShortNewsEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "image")
    private String image;
    @Column(name = "title")
    private String title;
    @Column(name = "news_date")
    private String date;
    @Column(name = "short_news")
    private String shortNewsTest;
    @Column(name = "description_link")
    private String descriptionLink;

    @OneToOne(mappedBy = "shortNews")
    private FullNewsEntity fullNewsEntity;


    public ShortNewsEntity() {
    }

    public ShortNewsEntity(String image, String title, String date, String shortNewsText, String descriptionLink) {
        this.image = image;
        this.title = title;
        this.date = date;
        this.shortNewsTest = shortNewsText;
        this.descriptionLink = descriptionLink;
    }

}

package com.bortn.ddutBlog.entity.news;

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

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;
    @Column(name = "title", columnDefinition = "TEXT")
    private String title;
    @Column(name = "news_date", columnDefinition = "TEXT")
    private String date;
    @Column(name = "short_news", columnDefinition = "TEXT")
    private String shortNewsText;
    @Column(name = "description_link", columnDefinition = "TEXT")
    private String descriptionLink;

    @OneToOne(mappedBy = "shortNews")
    private FullNewsEntity fullNewsEntity;


    public ShortNewsEntity() {
    }

    public ShortNewsEntity(String image, String title, String date, String shortNewsText, String descriptionLink) {
        this.image = image;
        this.title = title;
        this.date = date;
        this.shortNewsText = shortNewsText;
        this.descriptionLink = descriptionLink;
    }

}

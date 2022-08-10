package com.bortn.ddutBlog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@Table(name = "full_news")
public class FullNewsEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full_text", columnDefinition="TEXT")
    private String fullText;


    @OneToOne
    @JoinColumn(name = "short_news_id", referencedColumnName = "id")
    private ShortNewsEntity shortNews;

    @OneToMany(
            mappedBy = "fullNewsEntity",
            cascade = CascadeType.ALL
    )
    private List<ImagesEntity> imagesEntity;

    @OneToMany(
            mappedBy = "fullNewsEntity",
            cascade = CascadeType.ALL
    )
    private List<LinkDocumentsEntity> linkDocumentsEntities;

    public FullNewsEntity() {
    }

    public FullNewsEntity(String fullText) {
        this.fullText = fullText;
    }

    public void setShortNews(ShortNewsEntity shortNews) {
        this.shortNews = shortNews;
    }


}

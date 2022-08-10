package com.bortn.ddutBlog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "images")
public class ImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "full_news_id")
    private FullNewsEntity fullNewsEntity;


    public ImagesEntity() {
    }

    public ImagesEntity(String image, FullNewsEntity fullNewsEntity) {
        this.image = image;
        this.fullNewsEntity = fullNewsEntity;
    }
}

package com.bortn.ddutBlog.entity.album;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "gallery_img")
public class GalleryImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gallery_entity_id")
    private GalleryEntity galleryEntity;


    public GalleryImagesEntity() {
    }

    public GalleryImagesEntity(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

package com.bortn.ddutBlog.entity.album;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "photo")
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gallery_entity_id")
    private GalleryEntity galleryEntity;


    public PhotoEntity() {
    }

    public PhotoEntity(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

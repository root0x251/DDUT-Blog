package com.bortn.ddutBlog.entity.album;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "gallery")
public class GalleryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    @Column(name = "text_about")
    private String textAbout;
    @Column(name = "read_more")
    private String readMoreLink;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    private AlbumEntity albumEntity;

    @OneToMany
    private List<GalleryImagesEntity> galleryImagesEntities;

    @OneToMany
    private List<PhotoEntity> photoEntities;

    public GalleryEntity() {
    }

    public GalleryEntity(String title, String textAbout, String readMoreLink) {
        this.title = title;
        this.textAbout = textAbout;
        this.readMoreLink = readMoreLink;
    }
}

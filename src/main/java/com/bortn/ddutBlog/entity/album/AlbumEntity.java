package com.bortn.ddutBlog.entity.album;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "album")
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "album_date")
    private String date;
    private String title;
    private String about;
    @Column(name = "img_url")
    private String imgUrl;
    @Column(name = "read_more_link")
    private String readMoreLink;


    @OneToMany (
            mappedBy = "albumEntity",
            cascade = CascadeType.ALL
    )
    private List<GalleryEntity> galleryEntity;

    public AlbumEntity() {
    }

    public AlbumEntity(String date, String title, String about, String imgUrl, String readMoreLink) {
        this.date = date;
        this.title = title;
        this.about = about;
        this.imgUrl = imgUrl;
        this.readMoreLink = readMoreLink;
    }
}

package com.bortn.ddutBlog.controller;

import com.bortn.ddutBlog.entity.album.AlbumEntity;
import com.bortn.ddutBlog.entity.album.GalleryEntity;
import com.bortn.ddutBlog.entity.album.GalleryImagesEntity;
import com.bortn.ddutBlog.entity.album.PhotoEntity;
import com.bortn.ddutBlog.repository.album.AlbumRepo;
import com.bortn.ddutBlog.repository.album.GalleryImgRepo;
import com.bortn.ddutBlog.repository.album.GalleryRepo;
import com.bortn.ddutBlog.repository.album.PhotoRepo;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
public class AlbumController {

    @Autowired
    private AlbumRepo albumRepo;

    @Autowired
    private GalleryRepo galleryRepo;

    @Autowired
    private GalleryImgRepo galleryImgRepo;

    @Autowired
    private PhotoRepo photoRepo;

    @PostMapping("/album")
    public void test() {



        ArrayList<PhotoEntity> photoEntityArrayList = new ArrayList<>();
        ArrayList<GalleryImagesEntity> galleryEntityArrayList = new ArrayList<>();
        List<GalleryEntity> galleryEntities = new ArrayList<>();
        AlbumEntity album = new AlbumEntity("date","title", "about", "img url", "read more link");

        for (int i = 0; i < 20; i++) {
            GalleryEntity gallery = new GalleryEntity("title", "text about", "read more link");
            galleryEntities.add(gallery);
        }
        for (int i = 0; i < 6; i++) {
            GalleryImagesEntity galleryImages = new GalleryImagesEntity("img url");
            galleryEntityArrayList.add(galleryImages);
        }
        for (int i = 0; i < 20; i++) {
            PhotoEntity photoEntity = new PhotoEntity("img url");
            photoEntityArrayList.add(photoEntity);
        }

//        album.setGalleryEntity(galleryEntities);
////        albumRepo.save(album);
//        gallery.setGalleryImagesEntities(galleryEntityArrayList);
//        gallery.setPhotoEntities(photoEntityArrayList);
//        gallery.setAlbumEntity(album);
//        galleryRepo.save(gallery);
    }

}

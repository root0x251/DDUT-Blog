package com.bortn.ddutBlog.repository.album;

import com.bortn.ddutBlog.entity.album.GalleryImagesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryImgRepo extends CrudRepository<GalleryImagesEntity, Long> {
}

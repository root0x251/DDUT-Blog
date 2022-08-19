package com.bortn.ddutBlog.repository.album;

import com.bortn.ddutBlog.entity.album.GalleryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepo extends CrudRepository<GalleryEntity, Long> {
}

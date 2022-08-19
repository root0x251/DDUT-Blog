package com.bortn.ddutBlog.repository.album;


import com.bortn.ddutBlog.entity.album.AlbumEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepo extends CrudRepository<AlbumEntity, Long> {
}


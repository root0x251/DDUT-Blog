package com.bortn.ddutBlog.repository.album;

import com.bortn.ddutBlog.entity.album.PhotoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepo extends CrudRepository<PhotoEntity, Long> {
}

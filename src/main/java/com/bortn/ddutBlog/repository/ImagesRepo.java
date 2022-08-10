package com.bortn.ddutBlog.repository;

import com.bortn.ddutBlog.entity.ImagesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepo extends CrudRepository<ImagesEntity, Long> {
}

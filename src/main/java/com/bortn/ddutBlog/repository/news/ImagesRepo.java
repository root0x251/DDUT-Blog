package com.bortn.ddutBlog.repository.news;

import com.bortn.ddutBlog.entity.news.ImagesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepo extends CrudRepository<ImagesEntity, Long> {
}

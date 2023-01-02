package com.bortn.ddutBlog.repository.news;

import com.bortn.ddutBlog.entity.news.LinkDocumentsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkDocumentsRepo extends CrudRepository<LinkDocumentsEntity, Long> {
}
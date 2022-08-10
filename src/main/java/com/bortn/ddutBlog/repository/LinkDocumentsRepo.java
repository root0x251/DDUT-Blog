package com.bortn.ddutBlog.repository;

import com.bortn.ddutBlog.entity.LinkDocumentsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkDocumentsRepo extends CrudRepository<LinkDocumentsEntity, Long> {
}
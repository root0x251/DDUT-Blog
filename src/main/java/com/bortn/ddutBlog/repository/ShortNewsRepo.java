package com.bortn.ddutBlog.repository;

import com.bortn.ddutBlog.entity.ShortNewsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortNewsRepo extends CrudRepository<ShortNewsEntity, Long> {
}

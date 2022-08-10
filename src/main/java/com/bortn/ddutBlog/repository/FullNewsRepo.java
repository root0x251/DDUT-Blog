package com.bortn.ddutBlog.repository;

import com.bortn.ddutBlog.entity.FullNewsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FullNewsRepo extends CrudRepository<FullNewsEntity, Long> {

}

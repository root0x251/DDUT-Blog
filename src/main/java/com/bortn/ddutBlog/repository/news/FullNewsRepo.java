package com.bortn.ddutBlog.repository.news;

import com.bortn.ddutBlog.entity.news.FullNewsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FullNewsRepo extends CrudRepository<FullNewsEntity, Long> {

}

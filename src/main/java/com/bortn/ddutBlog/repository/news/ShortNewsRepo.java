package com.bortn.ddutBlog.repository.news;

import com.bortn.ddutBlog.entity.news.ShortNewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortNewsRepo extends JpaRepository<ShortNewsEntity, Long> {
}

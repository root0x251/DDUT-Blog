package com.bortn.ddutBlog;


import com.bortn.ddutBlog.entity.news.ShortNewsEntity;
import com.bortn.ddutBlog.repository.news.ShortNewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortNewsService {

    @Autowired
    private ShortNewsRepo shortNewsRepo;

    public List<ShortNewsEntity> findAll() {
        return shortNewsRepo.findAll();
    }

    public ShortNewsEntity findById(Long id) {
        return shortNewsRepo.getReferenceById(id);
    }

}

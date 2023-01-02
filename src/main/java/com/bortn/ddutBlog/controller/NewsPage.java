package com.bortn.ddutBlog.controller;

import com.bortn.ddutBlog.entity.news.ShortNewsEntity;
import com.bortn.ddutBlog.repository.news.ShortNewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsPage {

    @Autowired
    private ShortNewsRepo shortNewsRepo;

    @GetMapping("/")
    public String helloWorld(Model model) {
        List<ShortNewsEntity> shortNewsEntityList = shortNewsRepo.findAll();
        model.addAttribute("shortNews", shortNewsEntityList);
        return "index.html";
    }

    @GetMapping("/2")
    public String getNewsID(Model model) {
        List<ShortNewsEntity> shortNewsEntityList = shortNewsRepo.findAll();
        model.addAttribute("shortNews", shortNewsEntityList);
        return "index.html";
    }

}

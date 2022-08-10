package com.bortn.ddutBlog.controller;

import com.bortn.ddutBlog.entity.FullNewsEntity;
import com.bortn.ddutBlog.entity.ImagesEntity;
import com.bortn.ddutBlog.entity.LinkDocumentsEntity;
import com.bortn.ddutBlog.entity.ShortNewsEntity;
import com.bortn.ddutBlog.jsoup.Parser;
import com.bortn.ddutBlog.repository.FullNewsRepo;
import com.bortn.ddutBlog.repository.ImagesRepo;
import com.bortn.ddutBlog.repository.LinkDocumentsRepo;
import com.bortn.ddutBlog.repository.ShortNewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MainController {

    @Autowired
    ShortNewsRepo shortNewsRepo;
    @Autowired
    FullNewsRepo fullNewsRepo;
    @Autowired
    ImagesRepo imagesRepo;
    @Autowired
    LinkDocumentsRepo linkDocumentsRepo;

    @PostMapping("/test")
    public void TestPostMethod(String title, String date, String shortNewsText, String descriptionLink, String[] images, String fullText, String[] linkToDocuments, String image) {
        ArrayList<ImagesEntity> imagesEntityArrayList = new ArrayList<>();
        ArrayList<LinkDocumentsEntity> linkDocumentsEntityArrayList = new ArrayList<>();
        ShortNewsEntity shortNews = new ShortNewsEntity(image, title, date, shortNewsText, descriptionLink);
        shortNewsRepo.save(shortNews);
        FullNewsEntity fullNewsEntity = new FullNewsEntity(fullText);
        for (String s : images) {
            ImagesEntity imagesEntity = new ImagesEntity(s, fullNewsEntity);
            imagesRepo.save(imagesEntity);
            imagesEntityArrayList.add(imagesEntity);
        }
        for (String linkToDocument : linkToDocuments) {
            LinkDocumentsEntity linkDocumentsEntity = new LinkDocumentsEntity(linkToDocument, fullNewsEntity);
            linkDocumentsRepo.save(linkDocumentsEntity);
            linkDocumentsEntityArrayList.add(linkDocumentsEntity);
        }
        fullNewsEntity.setShortNews(shortNews);
        fullNewsEntity.setImagesEntity(imagesEntityArrayList);
        fullNewsEntity.setLinkDocumentsEntities(linkDocumentsEntityArrayList);
        fullNewsRepo.save(fullNewsEntity);
    }

    @PostMapping("/")
    public void start(int countOfPage) {
        Parser parser = new Parser();

        shortNewsRepo.save(parser.testShortNews(1));

    }


}

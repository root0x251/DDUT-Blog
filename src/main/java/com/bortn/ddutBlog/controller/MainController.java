package com.bortn.ddutBlog.controller;

import com.bortn.ddutBlog.entity.news.FullNewsEntity;
import com.bortn.ddutBlog.entity.news.ImagesEntity;
import com.bortn.ddutBlog.entity.news.LinkDocumentsEntity;
import com.bortn.ddutBlog.entity.news.ShortNewsEntity;
import com.bortn.ddutBlog.jsoup.Parser;
import com.bortn.ddutBlog.repository.news.FullNewsRepo;
import com.bortn.ddutBlog.repository.news.ImagesRepo;
import com.bortn.ddutBlog.repository.news.LinkDocumentsRepo;
import com.bortn.ddutBlog.repository.news.ShortNewsRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @PostMapping("/t")
    public void start() {
        parseShortNews(1);
    }


    public void parseShortNews(int countOfPages) {
        ShortNewsEntity shortNewsEntity = new ShortNewsEntity();
        try {
            for (int i = 1; i <= countOfPages; i++) {
                Document document = Jsoup.connect("https://ddut.vsevobr.ru/news/?page=" + i).get();
                Elements shortNewsElements = document.select("#lastNews > div.contentText");
                for (Element e : shortNewsElements) {
                    // image
                    String img = e.select("img").attr("abs:src");
                    String title = Objects.requireNonNull(e.select("h2").text());
                    String date = e.select("p.date").text();
                    String shortNewsText = e.select("p").not(".more").text();
                    String descriptionLink = Objects.requireNonNull(e.select("a").last()).attr("abs:href");
                    shortNewsEntity = new ShortNewsEntity(img, title, date, shortNewsText, descriptionLink);
                    shortNewsRepo.save(shortNewsEntity);
                    parseNews(shortNewsEntity, shortNewsEntity.getDescriptionLink());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    private void parseNews(ShortNewsEntity shortNews, String pageFullNews) {
        try {
            Document document = Jsoup.connect(pageFullNews).get();
            List<ImagesEntity> imagesEntitiesList = new ArrayList<>();
            List<LinkDocumentsEntity> linkDocumentsEntities = new ArrayList<>();
            StringBuilder fullText = new StringBuilder("");
            // full text
            Elements text = document.select("body > div.wrapper > div > div > main > div.aFull > div > p").not(".date").not("h2").not("em");
            for (Element element : text) {
                fullText.append(element.text());
            }
            FullNewsEntity fullNewsEntity = new FullNewsEntity(fullText.toString());
            // images
            Elements image = document.select("body > div.wrapper > div > div > main > div.aFull > div > div.articleImgBlock img");
            for (Element e : image) {
                ImagesEntity imagesEntity = new ImagesEntity(e.attr("abs:src"), fullNewsEntity);
                imagesEntitiesList.add(imagesEntity);
            }
            // link to documents
            Elements documents = document.select("body > div.wrapper > div > div > main > div.aFull > div a[href$=.pdf]");
            for (Element element : documents) {
                LinkDocumentsEntity linkDocumentsEntity = new LinkDocumentsEntity(element.attr("abs:href"), fullNewsEntity);
                linkDocumentsEntities.add(linkDocumentsEntity);
            }


            fullNewsEntity.setShortNews(shortNews);
            fullNewsEntity.setImagesEntity(imagesEntitiesList);
            fullNewsEntity.setLinkDocumentsEntities(linkDocumentsEntities);
            fullNewsRepo.save(fullNewsEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

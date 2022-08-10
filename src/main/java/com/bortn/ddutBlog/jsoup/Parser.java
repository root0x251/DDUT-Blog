package com.bortn.ddutBlog.jsoup;

import com.bortn.ddutBlog.entity.FullNewsEntity;
import com.bortn.ddutBlog.entity.ImagesEntity;
import com.bortn.ddutBlog.entity.LinkDocumentsEntity;
import com.bortn.ddutBlog.entity.ShortNewsEntity;
import com.bortn.ddutBlog.repository.FullNewsRepo;
import com.bortn.ddutBlog.repository.ImagesRepo;
import com.bortn.ddutBlog.repository.LinkDocumentsRepo;
import com.bortn.ddutBlog.repository.ShortNewsRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

public class Parser {

    @Autowired
    ShortNewsRepo shortNewsRepo;
    @Autowired
    FullNewsRepo fullNewsRepo;
    @Autowired
    ImagesRepo imagesRepo;
    @Autowired
    LinkDocumentsRepo linkDocumentsRepo;

    public ShortNewsEntity testShortNews(int countOfPages) {
        ShortNewsEntity shortNews = new ShortNewsEntity();
        try {
            for (int i = 1; i <= countOfPages; i++) {
                Document document = Jsoup.connect("https://ddut.vsevobr.ru/news/?page=" + i).get();
                Elements shortNewsElements = document.select("#lastNews > div.contentText");
                for (Element e : shortNewsElements) {
                    String image = (e.select("img")).attr("abs:src");
                    String title = Objects.requireNonNull(e.select("h2").text());
                    String date = e.select("p.date").text();
                    String shortNewsText = e.select("p").not(".more").text();
                    String descrLink = Objects.requireNonNull(e.select("a").last()).attr("abs:href");
                    shortNews.setDate(date);
                    shortNews.setImage(image);
                    shortNews.setTitle(title);
                    shortNews.setShortNewsTest(shortNewsText);
                    shortNews.setDescriptionLink(descrLink);
                    testParseNews(descrLink, shortNews);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shortNews;
    }


    public void testParseNews(String pageFullNews, ShortNewsEntity shortNews) {
        try {
            Document document = Jsoup.connect(pageFullNews).get();

            ArrayList<ImagesEntity> imagesEntityArrayList = new ArrayList<>();
            ArrayList<LinkDocumentsEntity> linkDocumentsEntityArrayList = new ArrayList<>();

            FullNewsEntity fullNewsEntity = new FullNewsEntity();
            // full text
            Elements text = document.select("body > div.wrapper > div > div > main > div.aFull > div > p").not(".date").not("h2").not("em");
            for (Element element : text) {
                fullNewsEntity.setFullText(element.text());
            }
            // images
            Elements image = document.select("body > div.wrapper > div > div > main > div.aFull > div > div.articleImgBlock img");
            for (Element e : image) {
                ImagesEntity imagesEntity = new ImagesEntity(e.attr("abs:src"), fullNewsEntity);
                imagesRepo.save(imagesEntity);
                imagesEntityArrayList.add(imagesEntity);
            }
            // link to documents
            Elements documents = document.select("body > div.wrapper > div > div > main > div.aFull > div a[href$=.pdf]");
            for (Element element : documents) {
                LinkDocumentsEntity linkDocumentsEntity = new LinkDocumentsEntity(element.attr("abs:href"), fullNewsEntity);
                linkDocumentsRepo.save(linkDocumentsEntity);
                linkDocumentsEntityArrayList.add(linkDocumentsEntity);
            }

            fullNewsEntity.setShortNews(shortNews);
            fullNewsEntity.setImagesEntity(imagesEntityArrayList);
            fullNewsEntity.setLinkDocumentsEntities(linkDocumentsEntityArrayList);
            fullNewsRepo.save(fullNewsEntity);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Set<String> parseShortNews(int countOfPages) {
        Set<String> shortNewsSet = new HashSet<>();
        try {
            for (int i = 1; i <= countOfPages; i++) {
                Document document = Jsoup.connect("https://ddut.vsevobr.ru/news/?page=" + i).get();
                Elements shortNewsElements = document.select("#lastNews > div.contentText");
                for (Element e : shortNewsElements) {
                    // image
                    shortNewsSet.add((e.select("img")).attr("abs:src"));
                    // title
                    shortNewsSet.add(Objects.requireNonNull(e.select("h2").text()));
                    // date
                    shortNewsSet.add(e.select("p.date").text());
                    //short news
                    shortNewsSet.add(e.select("p").not(".more").text());
                    // description link
                    shortNewsSet.add(Objects.requireNonNull(e.select("a").last()).attr("abs:href"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        shortNewsSet.forEach(System.out::println);

        return shortNewsSet;
    }

    private Set<String> parseNews(String pageFullNews) {
        try {
            Document document = Jsoup.connect(pageFullNews).get();

            // images
            Elements image = document.select("body > div.wrapper > div > div > main > div.aFull > div > div.articleImgBlock img");
            for (Element e : image) {
//                System.out.println(e.attr("abs:src"));
            }
            // link to documents
            Elements documents = document.select("body > div.wrapper > div > div > main > div.aFull > div a[href$=.pdf]");
            for (Element element : documents) {
//                System.out.println(element.attr("abs:href"));
            }
            // full text
            Elements text = document.select("body > div.wrapper > div > div > main > div.aFull > div > p").not(".date").not("h2").not("em");
            for (Element element : text) {
                System.out.println(element);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

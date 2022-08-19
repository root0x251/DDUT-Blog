package com.bortn.ddutBlog.jsoup;

import com.bortn.ddutBlog.entity.news.FullNewsEntity;
import com.bortn.ddutBlog.entity.news.ImagesEntity;
import com.bortn.ddutBlog.entity.news.LinkDocumentsEntity;
import com.bortn.ddutBlog.entity.news.ShortNewsEntity;
import com.bortn.ddutBlog.repository.news.FullNewsRepo;
import com.bortn.ddutBlog.repository.news.ImagesRepo;
import com.bortn.ddutBlog.repository.news.LinkDocumentsRepo;
import com.bortn.ddutBlog.repository.news.ShortNewsRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Parser {

    @Autowired
    ShortNewsRepo shortNewsRepo;
    @Autowired
    FullNewsRepo fullNewsRepo;
    @Autowired
    ImagesRepo imagesRepo;
    @Autowired
    LinkDocumentsRepo linkDocumentsRepo;

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
                    shortNewsEntity.setImage(img);
                    shortNewsEntity.setTitle(title);
                    shortNewsEntity.setDate(date);
                    shortNewsEntity.setShortNewsText(shortNewsText);
                    shortNewsEntity.setDescriptionLink(descriptionLink);
//                    shortNewsEntity = new ShortNewsEntity(img, title, date, shortNewsText, descriptionLink);
                    shortNewsRepo.save(shortNewsEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        parseNews(shortNewsEntity, shortNewsEntity.getDescriptionLink());

    }

    private void parseNews(ShortNewsEntity shortNews, String pageFullNews) {
        try {
            Document document = Jsoup.connect(pageFullNews).get();
            List<ImagesEntity> imagesEntitiesList = new ArrayList<>();
            List<LinkDocumentsEntity> linkDocumentsEntities = new ArrayList<>();
            String fullText = "";
            // full text
            Elements text = document.select("body > div.wrapper > div > div > main > div.aFull > div > p").not(".date").not("h2").not("em");
            for (Element element : text) {
                fullText = element.text();
            }
            FullNewsEntity fullNewsEntity = new FullNewsEntity(fullText);
            // images
            Elements image = document.select("body > div.wrapper > div > div > main > div.aFull > div > div.articleImgBlock img");
            for (Element e : image) {
                ImagesEntity imagesEntity = new ImagesEntity(e.text(), fullNewsEntity);
                imagesEntitiesList.add(imagesEntity);
            }
            // link to documents
            Elements documents = document.select("body > div.wrapper > div > div > main > div.aFull > div a[href$=.pdf]");
            for (Element element : documents) {
                LinkDocumentsEntity linkDocumentsEntity = new LinkDocumentsEntity(element.text(), fullNewsEntity);
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
//    public Set<String> parseShortNews(int countOfPages) {
//        Set<String> shortNewsSet = new HashSet<>();
//        try {
//            for (int i = 1; i <= countOfPages; i++) {
//                Document document = Jsoup.connect("https://ddut.vsevobr.ru/news/?page=" + i).get();
//                Elements shortNewsElements = document.select("#lastNews > div.contentText");
//                for (Element e : shortNewsElements) {
//                    // image
//                    shortNewsSet.add((e.select("img")).attr("abs:src"));
//                    // title
//                    shortNewsSet.add(Objects.requireNonNull(e.select("h2").text()));
//                    // date
//                    shortNewsSet.add(e.select("p.date").text());
//                    //short news
//                    shortNewsSet.add(e.select("p").not(".more").text());
//                    // description link
//                    shortNewsSet.add(Objects.requireNonNull(e.select("a").last()).attr("abs:href"));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        shortNewsSet.forEach(System.out::println);
//
//        return shortNewsSet;
//    }
//
//    private Set<String> parseNews(String pageFullNews) {
//        try {
//            Document document = Jsoup.connect(pageFullNews).get();
//
//            // images
//            Elements image = document.select("body > div.wrapper > div > div > main > div.aFull > div > div.articleImgBlock img");
//            for (Element e : image) {
////                System.out.println(e.attr("abs:src"));
//            }
//            // link to documents
//            Elements documents = document.select("body > div.wrapper > div > div > main > div.aFull > div a[href$=.pdf]");
//            for (Element element : documents) {
////                System.out.println(element.attr("abs:href"));
//            }
//            // full text
//            Elements text = document.select("body > div.wrapper > div > div > main > div.aFull > div > p").not(".date").not("h2").not("em");
//            for (Element element : text) {
//                System.out.println(element);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
}

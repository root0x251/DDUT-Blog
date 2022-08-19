package com.bortn.ddutBlog.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Objects;

public class AlbumParser {

    public void albumParser() {
        try {
            Document document = Jsoup.connect("https://ddut.vsevobr.ru/galereya/photo/").get();
            Elements album = document.select(".aList > div.aAnons");

            for (Element e : album) {
                // date (2015-2016)
                System.out.println(e.select("h2").text());
                // title (Фотоальбомы 2015 года.)
                System.out.println(Objects.requireNonNull(Objects.requireNonNull(e.select("p").first()).text()));
                // about (Обновлено: 05 Августа 2016 Всего альбомов: 16)
                System.out.println(Objects.requireNonNull(e.select("p.date")).text());
                // img url
                System.out.println(e.select("img").attr("abs:src"));
                // read more link
                String linkMore = e.select("p.readmore > a").attr("abs:href");
                if (!linkMore.isEmpty()) {
                    galleryParser(e.select("p.readmore > a").attr("abs:href"));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void galleryParser(String readMore) {
        try {
            Document document = Jsoup.connect(readMore).get();
            Elements gallery = document.select(".aList > div.aAnons");
            for (Element element : gallery) {
                // title
                System.out.println(element.select("h2").text());
                // text about
                System.out.println(element.select("p").text());
                // images
                Elements photo = document.select(".articleImgBlock");
                for (Element photoEl : photo) {
                    System.out.println(Objects.requireNonNull(photoEl.select("a").last()).attr("abs:href"));
                }
                // link more photo
                String linkMore = element.select("p.readmore > a").attr("abs:href");
                if (!linkMore.isEmpty()) {
                    galleryMoreParser(element.select("p.readmore > a").attr("abs:href"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void galleryMoreParser(String readMore) {
        try {
            Document document = Jsoup.connect(readMore).get();
            Elements photo = document.select("body > div.wrapper > div > div > main > div.aFull > div > div > div img");
            for (Element photoEl : photo) {
                System.out.println(photoEl.attr("abs:src"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

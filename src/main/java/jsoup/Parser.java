package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Parser {

    public void start() {
        parseShortNews(2);
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

//                System.out.println((e.select("img")).attr("src"));                                  //img
//                System.out.println(Objects.requireNonNull(e.select("h2").text()));                            // title
//                System.out.println(e.select("p.date").text());                                                // date
//                System.out.println(e.select("p").text());                                                     // short news
//                System.out.println(Objects.requireNonNull(e.select("a").last()).attr("href"));     // description
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

//            // author
//            Elements author = document.select("body > div.wrapper > div > div > main > div.aFull > div > p > em");
//            for (Element element : author) {
////                System.out.println(element.html());
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}



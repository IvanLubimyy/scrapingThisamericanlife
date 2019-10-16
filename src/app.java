import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class app {
    public static void main(String[] args) {
        try {
            // Here we create a document object and use JSoup to fetch the website
            Document doc = Jsoup.connect("https://www.thisamericanlife.org/archive?year=2019").get();

            // With the document fetched, we use JSoup's title() method to fetch the title
            System.out.printf("Title: %s\n", doc.title());

            // Get the list of repositories
            Elements repositories = doc.getElementsByClass("thumbnail goto goto-episode");

            for (Element repository : repositories) {
                // Extract the referents
                Element link = repository.select("a").first();
                String relHref = link.attr("href"); // == "/"
                String absHref = link.attr("abs:href");

                // go deep inside
                Document doc_inside = Jsoup.connect(absHref).get();

                //System.out.println(absHref);

            }
            // In case of any IO errors, we want the messages written to the console
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
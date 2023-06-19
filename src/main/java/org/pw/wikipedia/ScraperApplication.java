package org.pw.wikipedia;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pw.wikipedia.parsedelements.EventLink;
import org.pw.wikipedia.parser.EventsDetailsParser;
import org.pw.wikipedia.parser.PastEventsParser;

import java.io.IOException;
import java.util.List;


public class ScraperApplication {
    public static void main(String[] args) {
        ScraperApplication app = new ScraperApplication();
        app.start();
    }

    public void start() {

        System.out.println(URL.BASE.getValue());
        Document doc;

        try {
            doc = Jsoup.connect(URL.BASE.getValue().concat(URL.UFC_EVENTS.getValue())).get();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Connecting to URL: %s went wrong".formatted(URL.BASE.getValue()));
        }
        PastEventsParser pastEventsParser = new PastEventsParser();
        List<EventLink> pastEvents =  pastEventsParser.parse(doc);
//        EventsDetailsParser eventsDetailsParser = new EventsDetailsParser();
//        eventsDetailsParser.parse(doc);

    }
}

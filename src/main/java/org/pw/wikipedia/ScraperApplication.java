package org.pw.wikipedia;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pw.core.URLFetcher;
import org.pw.wikipedia.parsedelements.EventDetails;
import org.pw.wikipedia.parsedelements.EventLink;
import org.pw.wikipedia.parser.EventsDetailsParser;
import org.pw.wikipedia.parser.PastEventsParser;

import java.io.IOException;
import java.util.ArrayList;
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
            doc = URLFetcher.fetch(URL.BASE.getValue().concat(URL.UFC_EVENTS.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Connecting to URL: %s went wrong".formatted(URL.BASE.getValue()));
        }
        PastEventsParser pastEventsParser = new PastEventsParser();
        List<EventLink> pastEvents =  pastEventsParser.parse(doc);
        List<String> eventsUrls = pastEvents.stream().map(x -> URL.BASE.getValue().concat(x.getUrlPath())).toList();
        EventsDetailsParser eventsDetailsParser = new EventsDetailsParser();

        Document eventDoc;

        List<EventDetails> eventDetails = new ArrayList<>();
        for (var eventUrl : eventsUrls) {
            try {
                eventDoc = URLFetcher.fetch(eventUrl);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error occured during fetching url document");
            }

            eventDetails.add(eventsDetailsParser.parse(eventDoc).get(0));
        }

        eventDetails.forEach(x -> System.out.println(x));


    }
}

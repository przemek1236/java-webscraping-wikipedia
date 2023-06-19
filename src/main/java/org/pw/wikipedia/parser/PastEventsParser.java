package org.pw.wikipedia.parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.pw.core.Parser;
import org.pw.wikipedia.parsedelements.EventLink;

import java.util.ArrayList;
import java.util.List;

public class PastEventsParser implements Parser<EventLink> {
    @Override
    public List<EventLink> parse(Document doc) {
        List<EventLink> pastEventLinks = new ArrayList<>();
        Elements pastEventsElements = doc.select("table#Past_events > tbody td:nth-child(2)");
        for (var event : pastEventsElements) {
            var link = event.select("a");
            pastEventLinks.add(new EventLink(link.attr("href"), link.text()));
        }
        return pastEventLinks;
    }
}

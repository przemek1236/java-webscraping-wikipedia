package org.pw.wikipedia.parser;

import org.jsoup.nodes.Document;
import org.pw.core.Parser;
import org.pw.wikipedia.parsedelements.EventDetails;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class EventsDetailsParser implements Parser<EventDetails> {
    @Override
    public List<EventDetails> parse(Document doc) {
        // TODO finish implementation of parsing event details
        Elements elements = doc.select("table#Past_events > tbody");
        elements.forEach(x -> System.out.println(x.select("td")));
        return new ArrayList<EventDetails>();
    }
}

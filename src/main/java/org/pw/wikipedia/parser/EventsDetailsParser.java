package org.pw.wikipedia.parser;

import org.jsoup.nodes.Document;
import org.pw.core.Parser;
import org.pw.wikipedia.parsedelements.EventDetails;

import java.util.ArrayList;
import java.util.List;


public class EventsDetailsParser implements Parser<EventDetails> {
    @Override
    public List<EventDetails> parse(Document doc) {
        List<EventDetails> eventsDetails = new ArrayList<>();
        eventsDetails.add( new EventDetails(
                getTitle(doc),
                getDescription(doc),
                getInfoboxLabelInformation(doc, "Date"),
                getInfoboxLabelInformation(doc, "Venue"),
                getInfoboxLabelInformation(doc, "City"),
                getInfoboxLabelInformation(doc, "Attendance")
        ));


        return eventsDetails;
    }

    protected String getTitle(Document doc) {
        return doc.select("span.mw-page-title-main").text();
    }

    protected String getDescription(Document doc) {
        return doc.select("table.infobox tr").text();
    }

    protected String getInfoboxLabelInformation(Document doc, String label) {
        return doc.select("th.infobox-label:contains(%s) + td.infobox-data".formatted(label)).text();
    }
}

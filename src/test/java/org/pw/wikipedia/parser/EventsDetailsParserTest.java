package org.pw.wikipedia.parser;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.pw.core.JsoupDocumentMocker;
import org.pw.wikipedia.parsedelements.EventDetails;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@Tag("Parser")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EventsDetailsParserTest {

    String ufcSingleEventDetails;
    EventsDetailsParser eventsDetailsParser;

    @BeforeAll
    void setUp() {
        ufcSingleEventDetails = "src/test/resources/html/wikipedia_ufc_single_event_details.html";
        eventsDetailsParser = new EventsDetailsParser();
    }

    @Test
    void parse() {
        Document doc = JsoupDocumentMocker.readJsoupDocumentFromHtmlFile(ufcSingleEventDetails);
        List<EventDetails> eventDetails = eventsDetailsParser.parse(doc);

        assertThat(eventDetails).hasSize(1);
        EventDetails event = eventDetails.get(0);
        assertThat(event.getEventName()).isEqualTo("UFC on ESPN: Vettori vs. Cannonier");
        assertThat(event.getEventDate()).isEqualTo("2023-06-17");
        assertThat(event.getLocation()).isEqualTo("Enterprise, Nevada United States");
        assertThat(event.getVenue()).isEqualTo("UFC Apex");
    }
}
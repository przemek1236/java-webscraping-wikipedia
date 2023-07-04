package org.pw.wikipedia.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.pw.wikipedia.parsedelements.EventDetails;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EventsDetailsParserTest {

    EventsDetailsParser eventsDetailsParser;
    Document document;
    File htmlFile;

    @BeforeAll
    void setUp() throws IOException {
        htmlFile = new File("src/test/resources/html/wikipedia_ufc_single_event_details.html");
        document = Jsoup.parse(htmlFile, "UTF-8");
        eventsDetailsParser = new EventsDetailsParser();
    }

    @Test
    void parse() {
        List<EventDetails> eventDetails = eventsDetailsParser.parse(document);

        assertThat(eventDetails).hasSize(1);
        System.out.println(eventDetails.get(0).getEventDate());
        assertThat(eventDetails.get(0).getEventName()).isEqualTo("UFC on ESPN: Vettori vs. Cannonier");
        assertThat(eventDetails.get(0).getEventDate()).isEqualTo("2023-06-17");
        assertThat(eventDetails.get(0).getLocation()).isEqualTo("Enterprise, Nevada United States");
    }
}
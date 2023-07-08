package org.pw.wikipedia.parser;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.pw.core.JsoupDocumentMocker;
import org.pw.wikipedia.parsedelements.EventLink;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@Tag("Parser")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PastEventsParserTest {

    PastEventsParser pastEventsParser;
    Document doc;

    @BeforeAll
    void setUp() {
        pastEventsParser = new PastEventsParser();
        doc = JsoupDocumentMocker.readJsoupDocumentFromHtmlFile("src/test/resources/html/ufc_events.html");
    }

    @Test
    void parse() {
        List<EventLink> pastEvents = pastEventsParser.parse(doc);
        EventLink firstEvent = pastEvents.get(0);
        EventLink lastEvent = pastEvents.get(pastEvents.size() - 1);

        assertThat(pastEvents)
                .hasSize(8)
                .isInstanceOf(List.class);

        assertThat(firstEvent)
                .isInstanceOf(EventLink.class)
                .hasFieldOrPropertyWithValue("urlPath", "/wiki/UFC_on_ESPN:_Strickland_vs._Magomedov")
                .hasFieldOrPropertyWithValue("title", "UFC on ESPN: Strickland vs. Magomedov");

        assertThat(lastEvent)
                .isInstanceOf(EventLink.class)
                .hasFieldOrPropertyWithValue("urlPath", "/wiki/UFC_288")
                .hasFieldOrPropertyWithValue("title", "UFC 288: Sterling vs. Cejudo");

    }
}

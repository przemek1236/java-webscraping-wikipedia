package org.pw.utils;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.pw.core.JsoupDocumentMocker;
import org.pw.core.URLFetcher;
import org.pw.wikipedia.URL;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class URLFetcherTest {


    @Test
    void fetch() throws IOException {
        String validHtmlDocumentPath = "src/test/resources/html/wikipedia_ufc_single_event_details.html";
        String singleEventUrl = URL.BASE.getValue().concat(URL.UFC_EVENTS.getValue());

        try (MockedStatic<URLFetcher> urlFetcher = Mockito.mockStatic(URLFetcher.class)) {
            urlFetcher.when((MockedStatic.Verification) URLFetcher.fetch(singleEventUrl))
                    .thenReturn(JsoupDocumentMocker.readJsoupDocumentFromHtmlFile(validHtmlDocumentPath));
            Document doc = URLFetcher.fetch(singleEventUrl);

            assertThat(doc)
                    .isNotNull()
                    .isInstanceOf(Document.class);

            assertThat(doc.toString())
                    .isEqualTo(JsoupDocumentMocker.readJsoupDocumentFromHtmlFile(validHtmlDocumentPath).toString());
        }
    }
}
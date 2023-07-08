package org.pw.core;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@Tag("TestUtility")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JsoupDocumentMockerTest {

    String invalidHtmlFilePath;
    String validHtmlFilePath;

    @BeforeAll
    void setUp() {
        validHtmlFilePath = "src/test/resources/html/wikipedia_ufc_single_event_details.html";
        invalidHtmlFilePath = "src/test/resources/invalid.html";
    }

    @Test
    @DisplayName("readJsoupDocumentFromHtmlFile() - invalid html file")
    void readJsoupDocumentFromHtmlFileInvalidHtmlFile() {

        assertThatThrownBy(() -> JsoupDocumentMocker.readJsoupDocumentFromHtmlFile(invalidHtmlFilePath))
                .isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("readJsoupDocumentFromHtmlFile() - valid html file")
    void readJsoupDocumentFromHtmlFileValidHtmlFile() {
        assertThatCode(() -> {
            Document doc = JsoupDocumentMocker.readJsoupDocumentFromHtmlFile(validHtmlFilePath);
            assertThat(doc)
                    .isNotNull()
                    .isInstanceOf(Document.class);
        }).doesNotThrowAnyException();
    }
}

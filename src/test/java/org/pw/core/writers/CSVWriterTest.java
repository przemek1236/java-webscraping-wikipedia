package org.pw.core.writers;

import org.junit.jupiter.api.*;
import org.pw.wikipedia.parsedelements.EventDetails;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CSVWriterTest {
    private CSVWriter csvWriter;
    private List<EventDetails> eventDetails;
    private final String testFilePath = "src/test/resources/csv/test.csv";

    @BeforeAll
    void init() {
        csvWriter = new CSVWriter.Builder()
                .header(true)
                .delimiter(";")
                .headers(null)
                .build();

        eventDetails = new ArrayList<>(List.of(
                new EventDetails("event_1", "Description_1", "(2023-01-26)", "Venue", "Location 1", "Attendance 1[]"),
                new EventDetails("event_2", "Description_2", "(2023-01-26)", "Venue", "Location 2", "Attendance 2[]"),
                new EventDetails("event_3", "Description_3", "(2023-01-26)", "Venue", "Location 3", "Attendance 3[]"),
                new EventDetails("event_4", "Description_4", "(2023-01-26)", "Venue", "Location 4", "Attendance 4[]")
        ));
    }

    @Test
    @DisplayName("Test CSVWriter write to a file")
    void write() throws IOException {
        csvWriter.write(eventDetails, testFilePath);
        Path path = Paths.get(testFilePath);

        assertThat(Files.exists(path)).isTrue();

        String data = Files.readString(path);
        String[] data_rows = data.split("\n");

        assertThat(data_rows.length).isEqualTo(5);
        assertThat(data_rows[0]).isEqualTo("eventName;description;eventDate;venue;location;attendance");
        assertThat(data_rows[1]).isEqualTo("event_1;Description_1;2023-01-26;Venue;Location 1;Attendance 1[]");
    }


    @AfterAll
    void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get(testFilePath));
    }
}

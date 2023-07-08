package org.pw.wikipedia.parsedelements;

import org.pw.core.ParsedElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class EventDetails extends ParsedElement {

    private final String eventName;
    private final String description;
    private final LocalDate eventDate;
    private final String venue;
    private final String location;
    private final String attendance;


    public EventDetails(String eventName,
                        String description,
                        String eventDate,
                        String venue,
                        String location,
                        String attendance) {
        this.eventName = eventName;
        this.description = description;
        this.venue = venue;
        this.location = location;
        this.attendance = attendance;
        this.eventDate = parseDate(eventDate);
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getVenue() {
        return venue;
    }

    public String getLocation() {
        return location;
    }

    public String getAttendance() {
        return attendance;
    }

    private LocalDate parseDate(String date) {
        String[] splitDate = date.split(" ");
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();

        return LocalDate.parse(splitDate[splitDate.length - 1].substring(1, 11), dateTimeFormatter);

    }

    @Override
    public String toString() {
        return "|%s \t %s \t %s \t %s \t %s \t %s |".formatted(
                eventName,
                description.substring(0, 10) + "...",
                eventDate,
                venue,
                location,
                attendance
        );
    }
}

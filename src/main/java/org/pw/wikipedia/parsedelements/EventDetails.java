package org.pw.wikipedia.parsedelements;

import org.pw.core.ParsedElement;

import java.util.Date;

public class EventDetails extends ParsedElement {

    private final String eventName;
    private final String eventDate;
    private final String venue;
    private final String location;
    private final String attendance;


    public EventDetails(String eventName,
                        String eventDate,
                        String venue,
                        String location,
                        String attendance) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venue = venue;
        this.location = location;
        this.attendance = attendance;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
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

    @Override
    public String toString() {
        return "|%s \t %s \t %s \t %s \t %s |".formatted(
                eventName,
                eventDate,
                venue,
                location,
                attendance
        );
    }
}

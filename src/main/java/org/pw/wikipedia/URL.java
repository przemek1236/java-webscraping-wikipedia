package org.pw.wikipedia;

public enum URL {
    BASE("https://en.wikipedia.org/wiki"),
    UFC_EVENTS("/List_of_UFC_events");

    private final String value;


    URL(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

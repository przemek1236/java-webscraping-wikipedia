package org.pw.wikipedia;

public enum URL {
    BASE("https://en.wikipedia.org/"),
    UFC_EVENTS("/wiki/List_of_UFC_events");

    private final String value;


    URL(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

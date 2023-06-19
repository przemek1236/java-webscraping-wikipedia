package org.pw.wikipedia.parsedelements;

import org.pw.core.ParsedElement;

public class EventLink extends ParsedElement {
    private String urlPath;
    private String title;

    public EventLink() {}

    public EventLink(String urlPath, String title) {
        this.urlPath = urlPath;
        this.title = title;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "URL path: %4s, Title: %s".formatted(urlPath, title);
    }
}

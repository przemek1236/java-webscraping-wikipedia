package org.pw.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class URLFetcher {
    public static Document fetch(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}

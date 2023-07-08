package org.pw.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class JsoupDocumentMocker {
    public static Document readJsoupDocumentFromHtmlFile(String filePath) {
        try {
            File file = new File(filePath);
            return Jsoup.parse(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Error while reading test file in tests utility class: "
                    + JsoupDocumentMocker.class.getName());
        }
    }
}

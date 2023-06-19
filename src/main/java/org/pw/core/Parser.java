package org.pw.core;

import java.util.List;
import org.jsoup.nodes.Document;

public interface Parser<T extends ParsedElement> {
    List<T> parse(Document doc);
}

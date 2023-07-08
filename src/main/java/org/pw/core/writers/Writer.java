package org.pw.core.writers;

import org.pw.core.ParsedElement;

import java.util.List;

interface Writer {
    <T extends ParsedElement> void write(List<T> data, String path);
}

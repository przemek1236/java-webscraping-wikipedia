package org.pw.core.writers;

import org.pw.core.ParsedElement;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class CSVWriter implements Writer {

    private final String delimiter;
    private final boolean header;
    private final List<String> columns;

    private CSVWriter(Builder builder) {
        this.header = builder.header;
        this.delimiter = builder.delimiter;
        this.columns = builder.columns;
    }

    public static class Builder {
        private String delimiter;
        private boolean header;
        private List<String> columns;
        public Builder() {
            this.delimiter = ","; // default delimiter
            this.header = true; // default header
            this.columns = new ArrayList<>(); // default empty headers
        }
        public Builder header(boolean header) {
            this.header = header;
            return this;
        }

        public Builder delimiter(String delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        public Builder headers(List<String> columns) {
            this.columns = Objects.requireNonNullElseGet(columns, () -> new ArrayList<>());
            return this;
        }

        public CSVWriter build() {
            return new CSVWriter(this);
        }
    }


    @Override
    public <T extends ParsedElement> void write(List<T> data, String path) {
        if (data == null) {
            throw new RuntimeException("Data to write is null");
        }

        try ( FileWriter fileWriter = new FileWriter(path)) {
            if (header) {
                writeHeader(data.get(0), fileWriter);
            } else if (!columns.isEmpty()) {
                writeHeader(fileWriter);
            }

            writeData(data, fileWriter);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occured during writing to file");
        }
    }

    private <T extends ParsedElement> void writeData(List<T> data, FileWriter fileWriter) throws IOException {
       for (T element : data) {
           Field[] fields = element.getClass().getDeclaredFields();
           int fieldIdx = 0;
           String tmpDelimiter = this.delimiter;
           for (Field field : fields) {
               boolean isFieldAccessible = field.canAccess(element);
               try {
                   field.setAccessible(true);
                   Object value = field.get(element);
                   if (fieldIdx == fields.length-1) {
                       tmpDelimiter = "";
                   }
                   fileWriter.write(value != null ? value.toString() + tmpDelimiter : tmpDelimiter);
               } catch (IllegalAccessException e) {
                   throw new RuntimeException(e);
               } finally {
                   field.setAccessible(isFieldAccessible);
               }
               fieldIdx++;
           }
           fileWriter.write("\n");
       }
    }

    private <T extends ParsedElement> void writeHeader(T t, FileWriter fileWriter) throws IOException {
        int fieldIdx = 0;
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            fileWriter.write(field.getName() + (fieldIdx == fields.length-1 ? "": delimiter));
            fieldIdx++;
        }
        fileWriter.write("\n");

    }

    private void writeHeader(FileWriter fileWriter) throws IOException {
        int fieldIdx = 0;
        for (String column : columns) {
            fileWriter.write(column + (fieldIdx == columns.size()-1 ? "": delimiter));
        }
        fileWriter.write("\n");
    }
}

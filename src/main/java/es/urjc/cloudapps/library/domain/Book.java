package es.urjc.cloudapps.library.domain;

import java.util.Objects;

public class Book {

    private final Id id;
    private final String title;
    private final String summary;
    private final String author;
    private final String editorial;

    public Book(Id id, String title, String summary, String author, String editorial) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.editorial = editorial;
    }

    public Id getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSummary() {
        return this.summary;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public static class Id {
        private final String value;

        public Id(String value) {
            Long.parseLong(value);
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id bookId = (Id) o;
            return Objects.equals(value, bookId.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}

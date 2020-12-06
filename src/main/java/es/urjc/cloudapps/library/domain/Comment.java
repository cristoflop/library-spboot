package es.urjc.cloudapps.library.domain;

import java.util.Objects;

public class Comment {

    private final Id id;
    private final Book.Id bookId;
    private final String authorName;
    private final String body;
    private final Rating rating;

    public Comment(Id id, Book book, String authorName, String body, Rating rating) {
        this.id = id;
        this.bookId = book.getId();
        this.authorName = authorName;
        this.body = body;
        this.rating = rating;
    }

    public Id getId() {
        return id;
    }

    public Book.Id getBookId() {
        return bookId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBody() {
        return body;
    }

    public Rating getRating() {
        return this.rating;
    }

    public static class Id {
        private final String value;

        public Id(String value) {
            Integer.parseInt(value);
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
            Id commentId = (Id) o;
            return Objects.equals(value, commentId.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}

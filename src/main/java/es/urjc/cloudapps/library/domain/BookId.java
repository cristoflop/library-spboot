package es.urjc.cloudapps.library.domain;

import java.util.Objects;

public class BookId {

    private final String value;

    public BookId(String value) {
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
        BookId bookId = (BookId) o;
        return Objects.equals(value, bookId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

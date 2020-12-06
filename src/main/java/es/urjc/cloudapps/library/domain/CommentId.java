package es.urjc.cloudapps.library.domain;

import java.util.Objects;

public class CommentId {

    private final String value;

    public CommentId(String value) {
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
        CommentId commentId = (CommentId) o;
        return Objects.equals(value, commentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

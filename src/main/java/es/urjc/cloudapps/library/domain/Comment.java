package es.urjc.cloudapps.library.domain;

public class Comment {

    private final CommentId id;
    private final BookId bookId;
    private final String authorName;
    private final String body;
    private final Rating rating;

    public Comment(CommentId id, Book book, String authorName, String body, Rating rating) {
        this.id = id;
        this.bookId = book.getId();
        this.authorName = authorName;
        this.body = body;
        this.rating = rating;
    }

    public CommentId getId() {
        return id;
    }

    public BookId getBookId() {
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
}

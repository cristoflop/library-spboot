package es.urjc.cloudapps.library.domain;

public class Comment {

    private String id;
    private String bookId;
    private String authorName;
    private String body;
    private Rating rating;

    private int version;

    public Comment(String id, Book book, String authorName, String body, Rating rating) {
        this.id = id;
        this.bookId = book.getId();
        this.authorName = authorName;
        this.body = body;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getBookId() {
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

    public int getVersion() {
        return this.version;
    }
}

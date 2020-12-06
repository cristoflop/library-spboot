package es.urjc.cloudapps.library.application.dtos;

public class PublishCommentDto {
    private final String id;
    private final String authorName;
    private final String body;
    private final int rating;
    private String bookId;

    public PublishCommentDto(String id, String bookId, String authorName, String body, int rating) {
        this.id = id;
        this.bookId = bookId;
        this.authorName = authorName;
        this.body = body;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBody() {
        return body;
    }

    public int getRating() {
        return rating;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}

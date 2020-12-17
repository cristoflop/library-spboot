package es.urjc.cloudapps.library.application.dtos;

public class UserCommentDto {

    private final Long id;
    private final String body;
    private final Long bookId;
    private final int rating;

    public UserCommentDto(Long id, String body, Long bookId, int rating) {
        this.id = id;
        this.body = body;
        this.bookId = bookId;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Long getBookId() {
        return bookId;
    }

    public int getRating() {
        return rating;
    }
}

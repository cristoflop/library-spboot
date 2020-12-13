package es.urjc.cloudapps.library.application.dtos;

public class PublishCommentDto {
    private final String nick;
    private final String body;
    private final int rating;
    private Long bookId;

    public PublishCommentDto(Long bookId, String nick, String body, int rating) {
        this.bookId = bookId;
        this.nick = nick;
        this.body = body;
        this.rating = rating;
    }

    public String getNick() {
        return nick;
    }

    public String getBody() {
        return body;
    }

    public int getRating() {
        return rating;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

}

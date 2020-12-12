package es.urjc.cloudapps.library.application.dtos;

public class PublishCommentDto {
    private final String nick;
    private final String body;
    private final int rating;
    private String bookId;

    public PublishCommentDto(String bookId, String nick, String body, int rating) {
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

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}

package es.urjc.cloudapps.library.application.dtos;

public class CommentDto {

    private final Long id;
    private final String authorNick;
    private final String authorEmail;
    private final String body;

    public CommentDto(Long id, String authorNick, String authorEmail, String body) {
        this.id = id;
        this.authorNick = authorNick;
        this.authorEmail = authorEmail;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public String getAuthorNick() {
        return authorNick;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public String getBody() {
        return body;
    }

}

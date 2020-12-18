package es.urjc.cloudapps.library.application.dtos;

import java.util.List;

public class GetBookWithCommentsDto {

    private final Long id;
    private final String title;
    private final String summary;
    private final String author;
    private final String editorial;
    private final int publishYear;
    private final List<CommentDto> comments;
    private final Double rating;
    private final Long uploaderId;

    public GetBookWithCommentsDto(Long id,
                                  String title,
                                  String summary,
                                  String author,
                                  String editorial,
                                  int publishYear,
                                  Double rating, List<CommentDto> comments,
                                  Long uploaderId) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.editorial = editorial;
        this.publishYear = publishYear;
        this.rating = rating;
        this.comments = comments;
        this.uploaderId = uploaderId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getAuthor() {
        return author;
    }

    public String getEditorial() {
        return editorial;
    }

    public int getPublishYear() {
        return this.publishYear;
    }

    public Double getRating() {
        return rating;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public Long getUploaderId() {
        return uploaderId;
    }
}

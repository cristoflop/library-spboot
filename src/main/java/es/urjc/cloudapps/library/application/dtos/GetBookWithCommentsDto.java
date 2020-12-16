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
    private final double rating;
    private final String uploader;

    public GetBookWithCommentsDto(Long id,
                                  String title,
                                  String summary,
                                  String author,
                                  String editorial,
                                  int publishYear,
                                  double rating, List<CommentDto> comments,
                                  String uploader) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.editorial = editorial;
        this.publishYear = publishYear;
        this.rating = rating;
        this.comments = comments;
        this.uploader = uploader;
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

    public double getRating() {
        return rating;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public String getUploader() {
        return this.uploader;
    }
}

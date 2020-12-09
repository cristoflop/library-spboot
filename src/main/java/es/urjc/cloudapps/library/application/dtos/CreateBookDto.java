package es.urjc.cloudapps.library.application.dtos;

public class CreateBookDto {

    private final String title;
    private final String summary;
    private final String author;
    private final String editorial;
    private final String publishYear;

    public CreateBookDto(String title, String summary, String author, String editorial, String publishYear) {
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.editorial = editorial;
        this.publishYear = publishYear;
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

    public String getPublishYear() {
        return this.publishYear;
    }
}

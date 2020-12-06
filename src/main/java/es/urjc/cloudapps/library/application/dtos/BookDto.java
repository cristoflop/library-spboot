package es.urjc.cloudapps.library.application.dtos;

public class BookDto {

    private final String id;
    private final String title;

    public BookDto(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

}

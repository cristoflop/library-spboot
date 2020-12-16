package es.urjc.cloudapps.library.application.dtos;

public class BookDto {

    private final Long id;
    private final String title;

    public BookDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

}

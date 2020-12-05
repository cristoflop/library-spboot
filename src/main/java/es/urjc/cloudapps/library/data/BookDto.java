package es.urjc.cloudapps.library.data;

public class BookDto {

    private String id;
    private String title;

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

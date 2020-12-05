package es.urjc.cloudapps.library.data;

public class BookDto {

    private String uuid;
    private String title;

    public BookDto(String uuid, String title) {
        this.uuid = uuid;
        this.title = title;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getTitle() {
        return this.title;
    }

}

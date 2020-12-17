package es.urjc.cloudapps.library.domain;

import es.urjc.cloudapps.library.exception.FieldFormatException;

import javax.persistence.*;
import java.time.Year;

@Entity
@Table(name = "Books")
@Access(AccessType.FIELD)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String summary;

    private String author;

    private String editorial;

    private int publishYear;

    @ManyToOne
    private User uploader;

    protected Book() {
    }

    public Book(String title, String summary, String author, String editorial, Year parse, User user) {
        this(null, title, summary, author, editorial, parse, user);
    }

    public Book(Long id, String title, String summary, String author,
                String editorial, Year publishYear, User uploader) {
        this.checkTitle(title);
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.editorial = editorial;
        this.publishYear = publishYear.getValue();
        this.uploader = uploader;
    }

    private void checkTitle(String title) {
        if (title == null || title.trim().isEmpty())
            throw new FieldFormatException("El título no debe estar vacío");
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSummary() {
        return this.summary;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public int getPublishYear() {
        return this.publishYear;
    }

    public User getUploader() {
        return this.uploader;
    }

}

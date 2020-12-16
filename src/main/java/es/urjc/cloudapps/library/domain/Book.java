package es.urjc.cloudapps.library.domain;

import javax.persistence.*;

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

    public Book(Long id, String title, String summary, String author, String editorial, int publishYear, User uploader) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.editorial = editorial;
        this.publishYear = publishYear;
        this.uploader = uploader;
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

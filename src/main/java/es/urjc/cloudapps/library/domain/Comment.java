package es.urjc.cloudapps.library.domain;

import javax.persistence.*;

@Entity
@Table(name = "Comments")
@Access(AccessType.FIELD)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User author;

    private String body;

    @Embedded
    private Rating rating;

    public Comment() {
    }

    public Comment(Long id, Book book, User author, String body, Rating rating) {
        this.id = id;
        this.book = book;
        this.author = author;
        this.body = body;
        this.rating = rating;
    }

    public Comment(Book book, User user, String body, Rating rating) {
        this(null, book, user, body, rating);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public User getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public Rating getRating() {
        return rating;
    }
}

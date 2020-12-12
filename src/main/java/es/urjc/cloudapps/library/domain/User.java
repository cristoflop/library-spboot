package es.urjc.cloudapps.library.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String nick;

    @Embedded
    private Email email;

    protected User() {
    }

    @OneToMany
    @JoinColumn(name = "Comments", referencedColumnName = "id")
    private List<Comment> comments;

    @OneToMany
    @JoinColumn(name = "Books", referencedColumnName = "id")
    private List<Book> books;

    public User(Long id, String nick, String email, List<Comment> comments, List<Book> books) {
        this.id = id;
        this.nick = nick;
        this.email = email;
        this.comments = comments;
        this.books = books;
    }

    public Long getId() {
        return this.id;
    }

    public String getNick() {
        return this.nick;
    }

    public Email getEmail() {
        return this.email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

package es.urjc.cloudapps.library.domain;


import javax.persistence.*;

@Entity
@Table(name = "Users")
@Access(AccessType.FIELD)
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

    public User(Long id, String nick, Email email) {
        this.id = id;
        this.nick = nick;
        this.email = email;
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

}

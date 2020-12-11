package es.urjc.cloudapps.library.application.dtos;

public class UserDto {

    private Long id;
    private String nick;
    private String email;

    public UserDto(Long id, String nick, String email){
        this.id = id;
        this.nick = nick;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

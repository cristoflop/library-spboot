package es.urjc.cloudapps.library.application.dtos;

public class CreateUserDto {

    private final String nick;
    private final String email;

    public CreateUserDto(String nick, String email){
        this.nick = nick;
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public String getEmail() {
        return email;
    }

}

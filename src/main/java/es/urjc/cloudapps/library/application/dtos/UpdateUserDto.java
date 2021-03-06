package es.urjc.cloudapps.library.application.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UpdateUserDto {

    @JsonIgnore
    private Long id;
    private String email;

    public UpdateUserDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

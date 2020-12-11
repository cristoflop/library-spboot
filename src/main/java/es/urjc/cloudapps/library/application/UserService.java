package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.application.dtos.UserDto;
import es.urjc.cloudapps.library.data.UserJpaRepository;
import es.urjc.cloudapps.library.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserJpaRepository userJpaRepository;

    public UserService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public List<UserDto> getUsers() {
        Iterable<User> users = this.userJpaRepository.getUsers();
        return null;
    }

}

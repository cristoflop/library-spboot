package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.application.dtos.UserDto;
import es.urjc.cloudapps.library.data.UserJpaRepository;
import es.urjc.cloudapps.library.domain.User;
import es.urjc.cloudapps.library.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private UserJpaRepository userJpaRepository;

    public UserService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public List<UserDto> getUsers() {
        return StreamSupport.stream(this.userJpaRepository.getUsers().spliterator(), false)
                .map(user -> new UserDto(user.getId(), user.getNick(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public UserDto getUser(Long id) {
        User user = this.userJpaRepository.getUser(id).orElseThrow(UserNotFoundException::new);
        return new UserDto(user.getId(), user.getNick(), user.getEmail());
    }

    public UserDto getUser(String nick) {
        User user = this.userJpaRepository.getUser(nick).orElseThrow(UserNotFoundException::new);
        return new UserDto(user.getId(), user.getNick(), user.getEmail());
    }

}

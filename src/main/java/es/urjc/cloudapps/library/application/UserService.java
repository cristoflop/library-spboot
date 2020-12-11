package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.application.dtos.UserDto;
import es.urjc.cloudapps.library.data.UserJpaRepository;
import es.urjc.cloudapps.library.domain.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

}

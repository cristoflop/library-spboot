package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.application.dtos.CreateUserDto;
import es.urjc.cloudapps.library.application.dtos.UpdateUserDto;
import es.urjc.cloudapps.library.application.dtos.UserDto;
import es.urjc.cloudapps.library.data.CommentJpaRepository;
import es.urjc.cloudapps.library.data.UserJpaRepository;
import es.urjc.cloudapps.library.domain.Email;
import es.urjc.cloudapps.library.domain.User;
import es.urjc.cloudapps.library.exception.AssociatedCommentsException;
import es.urjc.cloudapps.library.exception.UserAlreadyExistsException;
import es.urjc.cloudapps.library.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserJpaRepository userJpaRepository;
    private final CommentJpaRepository commentJpaRepository;

    public UserService(UserJpaRepository userJpaRepository,
                       CommentJpaRepository commentJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.commentJpaRepository = commentJpaRepository;
    }

    public List<UserDto> getUsers() {
        return this.userJpaRepository
                .getUsers()
                .map(user -> new UserDto(user.getId(), user.getNick(), user.getEmail().getValue()))
                .collect(Collectors.toList());
    }

    public UserDto getUser(Long id) {
        User user = this.userJpaRepository
                .getUser(id)
                .orElseThrow(UserNotFoundException::new);
        return new UserDto(user.getId(),
                user.getNick(),
                user.getEmail().getValue());
    }

    public UserDto getUser(String nick) {
        User user = this.userJpaRepository
                .getUser(nick)
                .orElseThrow(UserNotFoundException::new);
        return new UserDto(user.getId(),
                user.getNick(),
                user.getEmail().getValue());
    }

    public Long createUser(CreateUserDto userDto) {
        User user = this.userJpaRepository
                .getUser(userDto.getNick())
                .orElse(null);
        if (user == null) {
            return this.userJpaRepository
                    .createOrUpdateUser(new User(null,
                            userDto.getNick(),
                            new Email(userDto.getEmail())))
                    .getId();
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    public Long updateUserEmail(UpdateUserDto userDto) {
        User user = this.userJpaRepository
                .getUser(userDto.getId())
                .orElseThrow(UserNotFoundException::new);
        user.setEmail(new Email(userDto.getEmail()));
        return this.userJpaRepository
                .createOrUpdateUser(user)
                .getId();
    }

    public void deleteUser(Long id) {
        User user = this.userJpaRepository
                .getUser(id)
                .orElseThrow(UserNotFoundException::new);
        if (this.commentJpaRepository.findAllOf(user).count() != 0) {
            throw new AssociatedCommentsException(user.getNick());
        }
        this.userJpaRepository.deleteUser(user);
    }

}

package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.application.dtos.CreateUserDto;
import es.urjc.cloudapps.library.application.dtos.UpdateUserDto;
import es.urjc.cloudapps.library.application.dtos.UserCommentDto;
import es.urjc.cloudapps.library.application.dtos.UserDto;
import es.urjc.cloudapps.library.data.CommentRepository;
import es.urjc.cloudapps.library.data.UserRepository;
import es.urjc.cloudapps.library.domain.Comment;
import es.urjc.cloudapps.library.domain.Email;
import es.urjc.cloudapps.library.domain.User;
import es.urjc.cloudapps.library.exception.AssociatedCommentsException;
import es.urjc.cloudapps.library.exception.UserAlreadyExistsException;
import es.urjc.cloudapps.library.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public UserService(UserRepository userRepository,
                       CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public List<UserDto> getUsers() {
        return this.userRepository
                .findAll().stream()
                .map(user -> new UserDto(user.getId(), user.getNick(), user.getEmail().getValue()))
                .collect(Collectors.toList());
    }

    public UserDto getUser(Long id) {
        User user = this.userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        return new UserDto(user.getId(),
                user.getNick(),
                user.getEmail().getValue());
    }

    public UserDto getUser(String nick) {
        User user = this.userRepository
                .findByNick(nick)
                .orElseThrow(UserNotFoundException::new);

        return new UserDto(user.getId(),
                user.getNick(),
                user.getEmail().getValue());
    }

    public Long createUser(CreateUserDto userDto) {
        Optional<User> user = this.userRepository
                .findByNick(userDto.getNick());

        if (user.isPresent())
            throw new UserAlreadyExistsException();

        User newUser = new User(
                userDto.getNick(),
                new Email(userDto.getEmail())
        );

        return this.userRepository
                .save(newUser)
                .getId();
    }

    public void updateUserEmail(UpdateUserDto userDto) {
        User user = this.userRepository
                .findById(userDto.getId())
                .orElseThrow(UserNotFoundException::new);

        user.setEmail(new Email(userDto.getEmail()));
    }

    public List<UserCommentDto> getUserComments(Long id) {
        User user = this.userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        return this.commentRepository.findAllByAuthor(user)
                .map(this::mapComment)
                .collect(Collectors.toList());
    }

    private UserCommentDto mapComment(Comment comment) {
        return new UserCommentDto(
                comment.getId(),
                comment.getBody(),
                comment.getBook().getId(),
                comment.getRating().getValue()
        );
    }

    public void deleteUser(Long id) {
        User user = this.userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        if (this.commentRepository.countAllByAuthor(user) > 0)
            throw new AssociatedCommentsException();

        this.userRepository.delete(user);
    }

}

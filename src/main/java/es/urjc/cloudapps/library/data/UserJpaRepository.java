package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class UserJpaRepository {

    private final UserSpringRepository userSpringRepository;

    public UserJpaRepository(UserSpringRepository userSpringRepository) {
        this.userSpringRepository = userSpringRepository;
    }

    public Stream<User> getUsers() {
        return this.userSpringRepository.findAll().stream();
    }

    public Optional<User> getUser(Long id) {
        return this.userSpringRepository.findById(id);
    }

    public Optional<User> getUser(String nick) {
        return this.userSpringRepository.findByNick(nick);
    }

    public User createOrUpdateUser(User user) {
        return this.userSpringRepository.save(user);
    }

    public void deleteUser(User user) {
        this.userSpringRepository.delete(user);
    }

}

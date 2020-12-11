package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserJpaRepository {

    private UserSpringRepository userSpringRepository;

    public UserJpaRepository(UserSpringRepository userSpringRepository) {
        this.userSpringRepository = userSpringRepository;
    }

    public Iterable<User> getUsers() {
        return this.userSpringRepository.findAll();
    }

    public User getUser(Long id) {
        return this.userSpringRepository.findById(id).orElse(null);
    }

    public User getUser(String nick) {
        return this.userSpringRepository.findByNick(nick).orElse(null);
    }

}

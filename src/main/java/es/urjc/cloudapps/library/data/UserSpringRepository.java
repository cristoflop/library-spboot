package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface UserSpringRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    Optional<User> findByNick(String nick);

}

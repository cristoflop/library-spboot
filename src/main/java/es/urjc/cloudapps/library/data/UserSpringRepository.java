package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSpringRepository extends CrudRepository<User, Long> {

    Optional<User> findByNick(String nick);

}

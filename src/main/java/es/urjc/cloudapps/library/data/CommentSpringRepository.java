package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CommentSpringRepository extends CrudRepository<Comment, Long> {
}

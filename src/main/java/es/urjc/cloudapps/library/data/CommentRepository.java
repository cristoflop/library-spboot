package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.Book;
import es.urjc.cloudapps.library.domain.Comment;
import es.urjc.cloudapps.library.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Query("SELECT AVG(c.rating) from Comment c where c.book = ?1")
    double getRatingAverageOf(Book book);

    Stream<Comment> findAllByBook(Book book);

    Stream<Comment> findAllByAuthor(User user);

    Long countAllByAuthor(User author);

}

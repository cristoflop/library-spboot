package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.Book;
import es.urjc.cloudapps.library.domain.Comment;
import es.urjc.cloudapps.library.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class CommentJpaRepository {

    private final CommentSpringRepository commentSpringRepository;

    public CommentJpaRepository(CommentSpringRepository commentSpringRepository) {
        this.commentSpringRepository = commentSpringRepository;
    }

    public Optional<Comment> getComment(Long id) {
        return this.commentSpringRepository.findById(id);
    }

    public Comment save(Comment comment) {
        return this.commentSpringRepository.save(comment);
    }

    public double getRatingAverageOf(Book book) {
        return this.commentSpringRepository.getRatingAverageOf(book);
    }

    public void delete(Comment comment) {
        this.commentSpringRepository.delete(comment);
    }

    public Stream<Comment> findAllOf(Book book) {
        return this.commentSpringRepository.findAllByBook(book);
    }

    public Stream<Comment> findAllOf(User user) {
        return this.commentSpringRepository.findAllByAuthor(user);
    }

}

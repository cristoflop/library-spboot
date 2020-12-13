package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.Book;
import es.urjc.cloudapps.library.domain.Comment;
import es.urjc.cloudapps.library.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CommentJpaRepository {

    private CommentSpringRepository commentSpringRepository;

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
        return StreamSupport.stream(this.commentSpringRepository.findAll().spliterator(), false)
                .filter(comment -> comment.getBook().getId().equals(book.getId()))
                .mapToInt(comment -> comment.getRating().getValue())
                .average()
                .orElse(0);
    }

    public void delete(Comment comment) {
        this.commentSpringRepository.delete(comment);
    }

    public List<Comment> findAllOf(Book book) {
        return this.commentSpringRepository.all()
                .filter(c -> c.getBook().equals(book))
                .collect(Collectors.toList());
    }

    public List<Comment> findAllOf(User user) {
        return this.commentSpringRepository.all()
                .filter(c -> c.getAuthor().equals(user))
                .collect(Collectors.toList());
    }

}

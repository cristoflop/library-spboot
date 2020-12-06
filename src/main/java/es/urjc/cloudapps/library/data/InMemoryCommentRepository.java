package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.Book;
import es.urjc.cloudapps.library.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryCommentRepository {

    private final Map<Comment.Id, Comment> comments;
    private final AtomicLong idGenerator;

    public InMemoryCommentRepository() {
        this.comments = new ConcurrentHashMap<>();
        this.idGenerator = new AtomicLong();
    }

    public Comment get(Comment.Id commentId) {
        return this.comments.get(commentId);
    }

    public void save(Comment comment) {
        comments.put(comment.getId(), comment);
    }

    public double getRatingAverageOf(Book book) {
        return this.comments
                .values()
                .stream()
                .filter(c -> c.getBookId().equals(book.getId()))
                .mapToInt(c -> c.getRating().getValue())
                .average()
                .orElse(0);
    }

    public void remove(Comment comment) {
        comments.remove(comment.getId());
    }

    public List<Comment> findAllOf(Book book) {
        return this.comments.values()
                .stream()
                .filter(c -> c.getBookId().equals(book.getId()))
                .collect(Collectors.toList());
    }

    public Comment.Id newId() {
        return new Comment.Id(String.valueOf(idGenerator.incrementAndGet()));
    }
}

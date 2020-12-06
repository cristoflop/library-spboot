package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.Book;
import es.urjc.cloudapps.library.domain.Comment;
import es.urjc.cloudapps.library.domain.CommentId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryCommentRepository {

    private final Map<CommentId, Comment> comments;
    private final AtomicLong idGenerator;

    public InMemoryCommentRepository() {
        this.comments = new ConcurrentHashMap<>();
        this.idGenerator = new AtomicLong();
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

    public Comment get(String id) {
        return comments.get(id);
    }

    public CommentId newId() {
        return new CommentId(String.valueOf(idGenerator.incrementAndGet()));
    }
}

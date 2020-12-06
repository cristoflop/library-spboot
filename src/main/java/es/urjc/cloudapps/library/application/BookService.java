package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.application.dtos.*;
import es.urjc.cloudapps.library.data.InMemoryBookRepository;
import es.urjc.cloudapps.library.data.InMemoryCommentRepository;
import es.urjc.cloudapps.library.domain.Book;
import es.urjc.cloudapps.library.domain.Comment;
import es.urjc.cloudapps.library.domain.Rating;
import es.urjc.cloudapps.library.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final InMemoryBookRepository bookRepository;
    private final InMemoryCommentRepository commentRepository;

    public BookService(InMemoryBookRepository bookRepository,
                       InMemoryCommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    public List<BookDto> getAllBooks() {
        return this.bookRepository.getAll().stream().map(
                book -> new BookDto(book.getId().getValue(), book.getTitle())).collect(Collectors.toList());
    }

    public String publishComment(PublishCommentDto comment) {
        Book.Id id = new Book.Id(comment.getBookId());
        Book book = bookRepository.get(id);

        if (book == null)
            throw new BookNotFoundException(comment.getBookId());

        Comment newComment = new Comment(
                commentRepository.newId(),
                book,
                comment.getAuthorName(),
                comment.getBody(),
                new Rating(comment.getRating())
        );

        commentRepository.save(newComment);

        return newComment.getId().toString();
    }

    public String create(CreateBookDto book) {
        Book newBook = new Book(
                bookRepository.newId(),
                book.getTitle(),
                book.getSummary(),
                book.getAuthor(),
                book.getEditorial(),
                Integer.parseInt(book.getPublishYear())
        );

        bookRepository.save(newBook);

        return newBook.getId().toString();
    }

    public GetBookWithCommentsDto getBookWithComments(String bookId) {
        Book.Id id = new Book.Id(bookId);
        Book book = bookRepository.get(id);
        if (book == null)
            throw new BookNotFoundException(bookId);

        // Este enfoque no sirve cuando el nivel de aislamiento
        // es inferior a REPEATABLE_READ, puesto que durante el tiempo
        // que pasa entre findAllOf() y getRatingAverageOf(), la primera
        // podría obtener una lista y la segunda calcular el rating
        // sobre una lista diferente.
        // TODO
        List<Comment> comments = commentRepository.findAllOf(book);
        double bookRating = commentRepository.getRatingAverageOf(book);

        List<CommentDto> mappedComments = comments
                .stream()
                .map(c -> new CommentDto(c.getId().toString(), c.getAuthorName(), c.getBody(), c.getRating().getValue()))
                .collect(Collectors.toList());

        return new GetBookWithCommentsDto(
                bookId,
                book.getTitle(),
                book.getSummary(),
                book.getAuthor(),
                book.getEditorial(),
                book.getPublishYear(),
                bookRating,
                mappedComments
        );
    }

    public void removeComment(String id) {
        Comment.Id commentId = new Comment.Id(id);
        Comment comment = commentRepository.get(commentId);
        commentRepository.remove(comment);
    }
}

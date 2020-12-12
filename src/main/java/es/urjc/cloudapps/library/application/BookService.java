package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.application.dtos.*;
import es.urjc.cloudapps.library.data.CommentJpaRepository;
import es.urjc.cloudapps.library.data.InMemoryBookRepository;
import es.urjc.cloudapps.library.data.UserJpaRepository;
import es.urjc.cloudapps.library.domain.Book;
import es.urjc.cloudapps.library.domain.Comment;
import es.urjc.cloudapps.library.domain.Rating;
import es.urjc.cloudapps.library.domain.User;
import es.urjc.cloudapps.library.exception.BookNotFoundException;
import es.urjc.cloudapps.library.exception.CommentNotFoundException;
import es.urjc.cloudapps.library.exception.FieldFormatException;
import es.urjc.cloudapps.library.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final InMemoryBookRepository bookRepository;
    private final CommentJpaRepository commentJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public BookService(InMemoryBookRepository bookRepository,
                       CommentJpaRepository commentJpaRepository,
                       UserJpaRepository userJpaRepository) {
        this.bookRepository = bookRepository;
        this.commentJpaRepository = commentJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    public List<BookDto> getAllBooks() {
        return this.bookRepository.getAll().stream().map(
                book -> new BookDto(book.getId().getValue(), book.getTitle())).collect(Collectors.toList());
    }

    public Long publishComment(PublishCommentDto comment) {
        this.checkPublishCommentDto(comment);

        Book.Id id = new Book.Id(comment.getBookId());
        Book book = bookRepository.get(id);
        if (book == null)
            throw new BookNotFoundException(comment.getBookId());

        User user = this.userJpaRepository.getUser(comment.getAuthorName()).orElseThrow(UserNotFoundException::new);
        Comment newComment = new Comment(
                null,
                book,
                user,
                comment.getBody(),
                new Rating(comment.getRating())
        );

        this.commentJpaRepository.save(newComment);
        return newComment.getId();
    }

    public String createBook(CreateBookDto book) {
        this.checkCreateBookDto(book);
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

        List<Comment> comments = this.commentJpaRepository.findAllOf(book);
        double bookRating = this.commentJpaRepository.getRatingAverageOf(book);

        List<CommentDto> mappedComments = comments
                .stream()
                .map(c -> new CommentDto(c.getId().toString(), c.getAuthor().getNick(), c.getBody(), c.getRating().getValue()))
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

    public void removeComment(String commentId) {
        Long id = Long.parseLong(commentId);
        Comment comment = this.commentJpaRepository.getComment(id).orElseThrow(CommentNotFoundException::new);
        this.commentJpaRepository.delete(comment);
    }

    private void checkCreateBookDto(CreateBookDto book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty())
            throw new FieldFormatException("El título no debe estar vacío");

        try {
            int year = Integer.parseInt(book.getPublishYear());
            if (year < 0) throw new FieldFormatException("Año de publicacion invalido");
        } catch (Exception e) {
            throw new FieldFormatException("Año de publicacion con formato incorrecto");
        }
    }

    private void checkPublishCommentDto(PublishCommentDto comment) {
        if (comment.getAuthorName() == null || comment.getAuthorName().trim().isEmpty())
            throw new FieldFormatException("El autor no debe estar vacío");
        if (comment.getBody() == null || comment.getBody().trim().isEmpty())
            throw new FieldFormatException("El comentario no debe estar vacío");
    }
}

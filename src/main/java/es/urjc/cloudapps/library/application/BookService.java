package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.application.dtos.*;
import es.urjc.cloudapps.library.data.BookJpaRepository;
import es.urjc.cloudapps.library.data.CommentJpaRepository;
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

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService {

    private final BookJpaRepository bookJpaRepository;
    private final CommentJpaRepository commentJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public BookService(BookJpaRepository bookJpaRepository,
                       CommentJpaRepository commentJpaRepository,
                       UserJpaRepository userJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
        this.commentJpaRepository = commentJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    public List<BookDto> getAllBooks() {
        return this.bookRepository.getAll().stream().map(
                book -> new BookDto(book.getId().getValue(), book.getTitle())).collect(Collectors.toList());
    }

    public Long publishComment(PublishCommentDto comment) {
        Book book = this.bookJpaRepository
                .getBook(Long.parseLong(comment.getBookId()))
                .orElseThrow(BookNotFoundException::new);
        User user = this.userJpaRepository
                .getUser(comment.getNick())
                .orElseThrow(UserNotFoundException::new);
        Comment newComment = new Comment(
                null,
                book,
                user,
                comment.getBody(),
                new Rating(comment.getRating())
        );
        return this.commentJpaRepository
                .save(newComment)
                .getId();
    }

    public Long createBook(CreateBookDto book) {
        this.checkCreateBookDto(book);
        User user = this.userJpaRepository
                .getUser(book.getUploader())
                .orElseThrow(UserNotFoundException::new);
        Book newBook = new Book(
                null,
                book.getTitle(),
                book.getSummary(),
                book.getAuthor(),
                book.getEditorial(),
                Integer.parseInt(book.getPublishYear()),
                user);
        return this.bookJpaRepository.save(newBook).getId();
    }

    public GetBookWithCommentsDto getBookWithComments(String bookId) {
        Book book = this.bookJpaRepository
                .getBook(Long.parseLong(bookId))
                .orElseThrow(BookNotFoundException::new);
        double bookRating = this.commentJpaRepository
                .getRatingAverageOf(book);
        List<CommentDto> mappedComments = this.commentJpaRepository
                .findAllOf(book)
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
                mappedComments,
                book.getUploader().getNick()
        );
    }

    public void removeComment(String commentId) {
        Long id = Long.parseLong(commentId);
        Comment comment = this.commentJpaRepository
                .getComment(id)
                .orElseThrow(CommentNotFoundException::new);
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
}

package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.application.dtos.*;
import es.urjc.cloudapps.library.data.BookRepository;
import es.urjc.cloudapps.library.data.CommentRepository;
import es.urjc.cloudapps.library.data.UserRepository;
import es.urjc.cloudapps.library.domain.Book;
import es.urjc.cloudapps.library.domain.Comment;
import es.urjc.cloudapps.library.domain.Rating;
import es.urjc.cloudapps.library.domain.User;
import es.urjc.cloudapps.library.exception.BookNotFoundException;
import es.urjc.cloudapps.library.exception.CommentNotFoundException;
import es.urjc.cloudapps.library.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,
                       CommentRepository commentRepository,
                       UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public List<BookDto> getAllBooks() {
        return this.bookRepository
                .findAll().stream()
                .map(this::mapBook)
                .collect(Collectors.toList());
    }

    private BookDto mapBook(Book book) {
        return new BookDto(book.getId(), book.getTitle());
    }

    public Long publishComment(PublishCommentDto comment) {
        Book book = this.bookRepository
                .findById(comment.getBookId())
                .orElseThrow(BookNotFoundException::new);

        User user = this.userRepository
                .findByNick(comment.getNick())
                .orElseThrow(UserNotFoundException::new);

        Comment newComment = new Comment(
                book,
                user,
                comment.getBody(),
                new Rating(comment.getRating())
        );

        return this.commentRepository
                .save(newComment)
                .getId();
    }

    public Long createBook(CreateBookDto book) {
        User user = this.userRepository
                .findByNick(book.getUploader())
                .orElseThrow(UserNotFoundException::new);

        Book newBook = new Book(
                book.getTitle(),
                book.getSummary(),
                book.getAuthor(),
                book.getEditorial(),
                Year.parse(book.getPublishYear()),
                user
        );

        return this.bookRepository.save(newBook).getId();
    }

    public GetBookWithCommentsDto getBookWithComments(Long bookId) {
        Book book = this.bookRepository
                .findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        double bookRating = this.commentRepository
                .getRatingAverageOf(book);

        List<CommentDto> mappedComments = this.commentRepository
                .findAllByBook(book)
                .map(this::mapComment)
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
                book.getUploader().getId()
        );
    }

    private CommentDto mapComment(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getAuthor().getNick(),
                comment.getAuthor().getEmail().getValue(),
                comment.getBody()
        );
    }

    public void removeComment(Long id) {
        Comment comment = this.commentRepository
                .findById(id)
                .orElseThrow(CommentNotFoundException::new);

        this.commentRepository.delete(comment);
    }
}

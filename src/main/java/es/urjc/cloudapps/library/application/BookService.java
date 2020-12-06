package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.application.dtos.CommentDto;
import es.urjc.cloudapps.library.application.dtos.CreateBookDto;
import es.urjc.cloudapps.library.application.dtos.GetBookWithCommentsDto;
import es.urjc.cloudapps.library.application.dtos.PublishCommentDto;
import es.urjc.cloudapps.library.data.InMemoryBookRepository;
import es.urjc.cloudapps.library.data.InMemoryCommentRepository;
import es.urjc.cloudapps.library.domain.Book;
import es.urjc.cloudapps.library.domain.BookId;
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

    public String publishComment(PublishCommentDto comment) {
        BookId id = new BookId(comment.getBookId());
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
                book.getEditorial()
        );

        bookRepository.save(newBook);

        return newBook.getId().toString();
    }

    public GetBookWithCommentsDto getBookWithComments(String bookId) {
        BookId id = new BookId(bookId);
        Book book = bookRepository.get(id);
        if (book == null)
            throw new BookNotFoundException(bookId);

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
                bookRating,
                mappedComments
        );
    }

    public void removeComment(String id) {
        Comment comment = commentRepository.get(id);
        commentRepository.remove(comment);
    }
}

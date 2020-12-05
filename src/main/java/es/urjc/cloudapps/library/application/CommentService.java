package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.data.InMemoryBookRepository;
import es.urjc.cloudapps.library.data.InMemoryCommentRepository;
import es.urjc.cloudapps.library.domain.Book;
import es.urjc.cloudapps.library.domain.Comment;
import es.urjc.cloudapps.library.domain.Rating;
import es.urjc.cloudapps.library.exception.BookNotFoundException;

import java.util.UUID;

public class CommentService {

    private InMemoryCommentRepository repository;
    private InMemoryBookRepository bookRepository;

    public String publishComment(String id, String bookId, String authorName, String body, int rating) {
        Book book = this.bookRepository.get(id);
        if(book == null)
            throw new BookNotFoundException(id);

        Comment newComment = new Comment(
                UUID.randomUUID().toString(),
                book,
                authorName,
                body,
                new Rating(rating)
        );

        book.newComment(newComment);

        bookRepository.save(book);
        repository.save(newComment);

        return newComment.getId();
    }

    public void removeComment(String id) {
        Comment comment = repository.get(id);
        if(comment == null)
            throw new BookNotFoundException(id);
        
        repository.remove(comment);
    }

}

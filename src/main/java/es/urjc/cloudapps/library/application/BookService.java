package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.data.BookDto;
import es.urjc.cloudapps.library.data.InMemoryBookRepository;
import es.urjc.cloudapps.library.domain.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class BookService {

    private InMemoryBookRepository bookRepository;

    public BookService(InMemoryBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        return this.bookRepository.getAll().stream().map(
                book -> new BookDto(book.getId(), book.getTitle())).collect(Collectors.toList());
    }

    public String create(String title, String summary, String author, String editorial) {
        Book newBook = new Book(
                UUID.randomUUID().toString(),
                title,
                summary,
                author,
                editorial
        );

        bookRepository.save(newBook);

        return newBook.getId();
    }

}

package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.data.BookDto;
import es.urjc.cloudapps.library.data.InMemoryBookRepository;
import es.urjc.cloudapps.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private InMemoryBookRepository bookRepository;

    public BookService(InMemoryBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> findAll() {
        return this.bookRepository.findAll();
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

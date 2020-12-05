package es.urjc.cloudapps.library.application;

import es.urjc.cloudapps.library.data.InMemoryBookRepository;
import es.urjc.cloudapps.library.domain.Book;

import java.util.UUID;

public class BookService {

    private InMemoryBookRepository repository;

    public String create(String title, String summary, String author, String editorial) {
        Book newBook = new Book(
                UUID.randomUUID().toString(),
                title,
                summary,
                author,
                editorial
        );

        repository.save(newBook);

        return newBook.getId();
    }

}

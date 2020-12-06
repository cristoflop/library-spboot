package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.Book;
import es.urjc.cloudapps.library.domain.BookId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryBookRepository {

    private final Map<BookId, Book> books;
    private final AtomicLong idGenerator;

    public InMemoryBookRepository() {
        this.books = new ConcurrentHashMap<>();
        this.idGenerator = new AtomicLong();
    }

    public void save(Book book) {
        this.books.put(book.getId(), book);
    }

    public BookId newId() {
        return new BookId(String.valueOf(idGenerator.incrementAndGet()));
    }

    public List<Book> getAll() {
        return new ArrayList<>(this.books.values());
    }

    public Book get(BookId id) {
        return this.books.get(id);
    }

}

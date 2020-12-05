package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryBookRepository {

    private Map<String, Book> books;

    public InMemoryBookRepository() {
        this.books = new HashMap<>();
        String uuid1 = UUID.randomUUID().toString();
        String uuid2 = UUID.randomUUID().toString();
        this.books.put(uuid1, new Book(uuid1, "Libro 1", "resumen", "cristofer", "shurs"));
        this.books.put(uuid2, new Book(uuid2, "Libro 2", "resumen", "cristofer", "shurs"));
    }

    public synchronized List<Book> getAll() {
        return new ArrayList<>(this.books.values());
    }

    public synchronized Book get(String id) {
        return this.books.get(id);
    }

    public synchronized void save(Book book) {
        this.checkVersion(book);

        books.put(book.getId(), book);
    }

    private void checkVersion(Book book) {
        Book oldBook = books.get(book.getId());

        if (book.getVersion() != oldBook.getVersion() + 1)
            throw new RuntimeException("Version is outdated");
    }

}

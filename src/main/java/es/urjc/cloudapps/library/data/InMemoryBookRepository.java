package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.Book;

import java.util.HashMap;
import java.util.Map;

public class InMemoryBookRepository {

    private Map<String, Book> books;

    public InMemoryBookRepository() {
        this.books = new HashMap<>();
    }

    public synchronized void save(Book book) {
        this.checkVersion(book);

        books.put(book.getId(), book);
    }

    public synchronized Book get(String id) {
        return this.books.get(id);
    }

    private void checkVersion(Book book) {
        Book oldBook = books.get(book.getId());

        if(book.getVersion() != oldBook.getVersion() + 1)
            throw new RuntimeException("Version is outdated");
    }

}

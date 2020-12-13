package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class BookJpaRepository {

    private BookSpringRepository bookSpringRepository;

    public BookJpaRepository(BookSpringRepository bookSpringRepository) {
        this.bookSpringRepository = bookSpringRepository;
    }

    public Stream<Book> getBooks() {
        return this.bookSpringRepository.all();
    }

    public Optional<Book> getBook(Long id) {
        return this.bookSpringRepository.findById(id);
    }

    public Book save(Book book) {
        return this.bookSpringRepository.save(book);
    }

}

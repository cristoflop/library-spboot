package es.urjc.cloudapps.library.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String id) {
        super(String.format("The book with id: %s doesn't exist", id));
    }

}

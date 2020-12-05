package es.urjc.cloudapps.library.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String uuid) {
        super("The book with id: " + uuid + " doesn't exist");
    }

}

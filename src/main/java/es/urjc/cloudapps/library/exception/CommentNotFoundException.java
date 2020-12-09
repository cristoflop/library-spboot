package es.urjc.cloudapps.library.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(String id) {
        super(String.format("The comment with id: %s doesn't exist", id));
    }

}

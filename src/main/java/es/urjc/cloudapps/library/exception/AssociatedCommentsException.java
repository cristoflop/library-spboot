package es.urjc.cloudapps.library.exception;

public class AssociatedCommentsException extends RuntimeException {

    public AssociatedCommentsException(String nick) {
        super("The user " + nick + " has made comments");
    }

}

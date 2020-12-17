package es.urjc.cloudapps.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User has associated comments")
public class AssociatedCommentsException extends RuntimeException {
}

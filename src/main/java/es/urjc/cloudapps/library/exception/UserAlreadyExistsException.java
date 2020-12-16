package es.urjc.cloudapps.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND, reason = "User already exists")
public class UserAlreadyExistsException extends RuntimeException {
}

package es.urjc.cloudapps.library.presentation;

import es.urjc.cloudapps.library.exception.FieldFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {FieldFormatException.class})
    public ResponseEntity<Error> fieldErrorException(FieldFormatException ex) {
        return ResponseEntity
                .badRequest()
                .body(Error.from(ex));
    }

    static class Error {

        private final String reason;

        public Error(String reason) {
            this.reason = reason;
        }

        public static Error from(Exception ex) {
            return new Error(ex.getMessage());
        }

        public String getReason() {
            return reason;
        }
    }

}

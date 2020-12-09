package es.urjc.cloudapps.library.presentation;

import es.urjc.cloudapps.library.application.BookService;
import es.urjc.cloudapps.library.application.dtos.BookDto;
import es.urjc.cloudapps.library.application.dtos.CreateBookDto;
import es.urjc.cloudapps.library.application.dtos.GetBookWithCommentsDto;
import es.urjc.cloudapps.library.application.dtos.PublishCommentDto;
import es.urjc.cloudapps.library.exception.BookNotFoundException;
import es.urjc.cloudapps.library.exception.CommentNotFoundException;
import es.urjc.cloudapps.library.exception.FieldFormatException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@Tag(name = "Books")
@RestController
@RequestMapping("/api")
public class RestBookController {

    private final BookService bookService;

    public RestBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    @Operation(summary = "Get all the books with their id and title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all the books",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDto.class))})})
    public ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok(
                this.bookService.getAllBooks());
    }

    @GetMapping("/books/{id}")
    @Operation(summary = "Get a book given its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetBookWithCommentsDto.class))}),})
    public ResponseEntity<GetBookWithCommentsDto> getBook(@PathVariable String id) {
        return ResponseEntity
                .ok(this.bookService.getBookWithComments(id));
    }

    @PostMapping("/books")
    @Operation(summary = "Create a new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The book has been created", headers = {
                    @Header(name = "Location", description = "URL where the new resource is located")
            }),})
    public ResponseEntity<Void> createBook(@Valid @RequestBody CreateBookDto newBook) {
        String bookId = this.bookService.createBook(newBook);

        URI newBookLocation = fromCurrentRequest()
                .path("/{bookId}")
                .buildAndExpand(bookId)
                .toUri();

        return ResponseEntity
                .created(newBookLocation)
                .build();
    }

    @PostMapping("/books/{bookId}/comments")
    @Operation(summary = "Publish a new comment about an specific book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The comment has been created"),})
    public ResponseEntity<Void> publishComment(@RequestBody PublishCommentDto commentDto, @PathVariable String bookId) {
        commentDto.setBookId(bookId);
        this.bookService.publishComment(commentDto);

        URI newCommentLocation = fromCurrentRequest()
                .replacePath("/api/books")
                .path("/{bookId}")
                .buildAndExpand(bookId)
                .toUri();

        return ResponseEntity
                .created(newCommentLocation)
                .build();
    }

    @DeleteMapping("/books/{bookId}/comments/{commentId}")
    @Operation(summary = "Delete a published comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The comment has been deleted"),})
    public ResponseEntity<Void> deleteComment(@PathVariable String commentId, @PathVariable String bookId) {
        this.bookService.removeComment(commentId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @ExceptionHandler(value = {BookNotFoundException.class, CommentNotFoundException.class})
    public ResponseEntity<Void> resourceNotFoundException() {
        return ResponseEntity
                .notFound()
                .build();
    }

    @ExceptionHandler(value = {FieldFormatException.class})
    public ResponseEntity<Error> fieldErrorException(FieldFormatException ex) {
        return ResponseEntity
                .badRequest()
                .body(Error.from(ex));
    }

    static class Error {

        private final String message;

        public Error(String message) {
            this.message = message;
        }

        public static Error from(Exception ex) {
            return new Error(ex.getMessage());
        }

        public String getMessage() {
            return message;
        }
    }

}

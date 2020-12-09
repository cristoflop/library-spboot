package es.urjc.cloudapps.library.presentation;

import es.urjc.cloudapps.library.application.BookService;
import es.urjc.cloudapps.library.application.dtos.BookDto;
import es.urjc.cloudapps.library.application.dtos.CreateBookDto;
import es.urjc.cloudapps.library.application.dtos.GetBookWithCommentsDto;
import es.urjc.cloudapps.library.application.dtos.PublishCommentDto;
import es.urjc.cloudapps.library.exception.BookNotFoundException;
import es.urjc.cloudapps.library.exception.CommentNotFoundException;
import es.urjc.cloudapps.library.exception.FieldFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WebBookController {

    private final WebUser webUser;
    private final BookService bookService;

    public WebBookController(BookService bookService, WebUser webUser) {
        this.webUser = webUser;
        this.bookService = bookService;
    }

    @GetMapping(value = {"/books", "/"})
    public String getAllIndexBooks(Model model) {
        List<BookDto> books = this.bookService.getAllBooks();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/books/{id}")
    public String getBook(@PathVariable("id") String id, Model model) {
        GetBookWithCommentsDto book = this.bookService.getBookWithComments(id);
        model.addAttribute("book", book);
        if (!this.webUser.isAnonymous())
            model.addAttribute("author", this.webUser.getUserName());
        return "book_detail";
    }

    @GetMapping("/books/add")
    public String addNewBook() {
        return "new_book";
    }

    @PostMapping("/books/add")
    public String addNewBook(@ModelAttribute("book") CreateBookDto book, Model model) {
        try {
            this.bookService.createBook(book);
            return "redirect:/books";
        } catch (FieldFormatException e) {
            model.addAttribute("error", new FieldError(e.getMessage()));
            return "new_book";
        }
    }

    @PostMapping("/books/{id}/comments")
    public String publishComment(@ModelAttribute PublishCommentDto comment,
                                 @PathVariable String id,
                                 RedirectAttributes flashAttributes) {
        try {
            this.bookService.publishComment(comment);
            this.webUser.setUserName(comment.getAuthorName());
        } catch (FieldFormatException e) {
            flashAttributes.addFlashAttribute("error", new FieldError(e.getMessage()));
        }
        return "redirect:/books/" + id;
    }

    @PostMapping("/books/{bookId}/comments/{commentId}")
    public String deleteComment(@PathVariable String bookId, @PathVariable String commentId) {
        this.bookService.removeComment(commentId);
        return "redirect:/books/" + bookId;
    }

    @ExceptionHandler(value = {BookNotFoundException.class, CommentNotFoundException.class})
    public String resourceNotFoundException() {
        return "redirect:/books/";
    }

    // clase para recorrer la lista de errores con mustache
    public static class FieldError {
        private final String field;

        public FieldError(String field) {
            this.field = field;
        }

        public String getField() {
            return this.field;
        }
    }


}

package es.urjc.cloudapps.library.presentation;

import es.urjc.cloudapps.library.application.BookService;
import es.urjc.cloudapps.library.application.dtos.BookDto;
import es.urjc.cloudapps.library.application.dtos.CreateBookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebBookController {

    private final BookService bookService;

    public WebBookController(BookService bookService) {
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
        return "book_detail";
    }

    @GetMapping("/books/add")
    public String addNewBook() {
        return "new_book";
    }

    @PostMapping("/books/add")
    public String addNewBook(@ModelAttribute("book") CreateBookDto book, Model model) {
        List<Field> fieldsWithError = new ArrayList<>();
        if (book.getTitle().isEmpty())
            fieldsWithError.add(new Field("Titulo"));
        try {
            int year = Integer.parseInt(book.getPublishYear());
            if (year < 0) throw new Exception();
        } catch (Exception e) {
            fieldsWithError.add(new Field("Año de publicacion"));
        }
        if (fieldsWithError.size() > 0) {
            model.addAttribute("errors", fieldsWithError);
            return "new_book";
        }
        this.bookService.create(book);
        return "redirect:/books";
    }

    // clase para recorrer la lista de errores con mustache
    public static class Field {
        private final String field;

        public Field(String field) {
            this.field = field;
        }

        public String getField() {
            return this.field;
        }
    }

}

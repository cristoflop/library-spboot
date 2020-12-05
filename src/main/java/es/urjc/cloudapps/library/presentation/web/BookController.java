package es.urjc.cloudapps.library.presentation.web;


import es.urjc.cloudapps.library.application.BookService;
import es.urjc.cloudapps.library.data.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String getAllBooks(Map<String, Object> model) {

        List<BookDto> books = this.bookService.findAll();
        model.put("books", books);

        return "index";
    }

}

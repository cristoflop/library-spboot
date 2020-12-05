package es.urjc.cloudapps.library.presentation.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class BookController {

    @GetMapping("/books")
    public ModelAndView getAllBooks(Map<String, Object> model) {
        System.out.println("ESTOY ENTRANDO AQUI");

        // ...

        return new ModelAndView("index", model);
    }

}

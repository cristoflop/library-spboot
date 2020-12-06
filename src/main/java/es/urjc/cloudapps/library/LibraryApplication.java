package es.urjc.cloudapps.library;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("\uD83D\uDCDA Library API")
                        .version("1.0")
                        .description("Welcome to Cloud Apps Library REST API. Kudos to Roy Fielding.")
                        .contact(new Contact().name("GitHub repository").url("https://github.com/juanaviladev/library-spboot"))
                );
    }

}

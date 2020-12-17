package es.urjc.cloudapps.library.presentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("\uD83D\uDCDA Library API")
                        .version("2.0")
                        .description("Welcome to Cloud Apps Library REST API. Kudos to Roy Fielding.")
                        .contact(new Contact().name("GitHub repository").url("https://github.com/juanaviladev/library-spboot"))
                );
    }

}

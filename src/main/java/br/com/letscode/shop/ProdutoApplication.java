package br.com.letscode.shop;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Ecommerce API", version = "1.0", description = "Information about ecommerce API"))
public class ProdutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdutoApplication.class, args);
    }

    /*@Bean
    CommandLineRunner run(UsuarioService usuarioService) {
        return args -> {
            usuarioService.criar(new UsuarioRequest("aramiz", "1234", "ADMIN", "Aramiz", LocalDate.of(1984, 4, 23), ZonedDateTime.now(), ZonedDateTime.now()));
        };
    }*/

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

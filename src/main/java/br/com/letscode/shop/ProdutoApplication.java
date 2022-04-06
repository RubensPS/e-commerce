package br.com.letscode.shop;

import br.com.letscode.shop.usuario.UsuarioRequest;
import br.com.letscode.shop.usuario.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@SpringBootApplication
public class ProdutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdutoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UsuarioService usuarioService) {
        return args -> {
            usuarioService.criar(new UsuarioRequest("aramiz", "1234", "ADMIN", "Aramiz", LocalDate.of(1984, 4, 23), ZonedDateTime.now(), ZonedDateTime.now()));
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

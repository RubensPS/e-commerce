package br.com.letscode.shop.usuario;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsuarioRequest {

    private String nomeUsuario;
    private String password;
    private String funcao;
    private String nome;
    @DateTimeFormat
    private LocalDate dataNascimento;
    private ZonedDateTime dataCriacao;
    private ZonedDateTime dataAtualizacao;

    public UsuarioEntity toEntity() {
        return new UsuarioEntity(
                this.getNomeUsuario(),
                this.getPassword(),
                this.getFuncao(),
                this.getNome(),
                this.getDataNascimento());
    }

    public UsuarioRequest(String nomeUsuario, String password, String funcao, String nome, LocalDate dataNascimento) {
        this.nomeUsuario = nomeUsuario;
        this.password = password;
        this.funcao = funcao;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataCriacao = ZonedDateTime.now();
        this.dataAtualizacao = ZonedDateTime.now();
    }
}

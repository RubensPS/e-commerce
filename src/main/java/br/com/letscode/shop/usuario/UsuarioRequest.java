package br.com.letscode.shop.usuario;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsuarioRequest {

    private String userName;
    private String password;
    private String name;
    private Date dataNascimento;
    private ZonedDateTime dataCriacao;
    private ZonedDateTime dataAtualizacao;

    public UsuarioEntity toEntity() {
        return new UsuarioEntity(
                this.getUserName(),
                this.getPassword(),
                this.getName(),
                this.getDataNascimento());
    }
}

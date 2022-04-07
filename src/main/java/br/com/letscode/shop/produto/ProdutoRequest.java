package br.com.letscode.shop.produto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutoRequest {

    private String nome;
    private String descricao;
    private BigDecimal valor;
    @JsonProperty("codigoBarra")
    private String codigoBarra;
    @JsonProperty("nomeFabricante")
    private String nomeFabricante;
    private Integer peso;
    @JsonProperty("pesoUnidadeMedida")
    private String pesoUnidadeMedida;

}

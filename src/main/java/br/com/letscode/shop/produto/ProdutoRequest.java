package br.com.letscode.shop.produto;

import br.com.letscode.shop.fabricante.FabricanteEntity;
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
    private String codigoBarra;
    private FabricanteEntity fabricante;
    private Integer peso;
    private String pesoUnidadeMedida;


    public ProdutoEntity toEntity() {
        return new ProdutoEntity(
                this.getNome(),
                this.getDescricao(),
                this.getValor(),
                this.getCodigoBarra(),
                this.getFabricante(),
                this.getPeso(),
                this.getPesoUnidadeMedida());
    }
}

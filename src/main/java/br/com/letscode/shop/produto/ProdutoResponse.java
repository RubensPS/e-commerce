package br.com.letscode.shop.produto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutoResponse {

    private long id;
    private UUID codigo;
    private String codigoBarra;
    private String descricao;
    private String fabricante;
    private Integer peso;
    private String pesoUnidadeMedida;
    private BigDecimal valor;


    public ProdutoResponse(ProdutoEntity produto) {
        this.id = produto.getId();
        this.codigo = produto.getCodigo();
        this.codigoBarra = produto.getCodigoBarra();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.peso = produto.getPeso();
        this.fabricante = produto.getFabricante().getNomeFabricante();
        this.pesoUnidadeMedida = produto.getPesoUnidadeMedida();
    }

}
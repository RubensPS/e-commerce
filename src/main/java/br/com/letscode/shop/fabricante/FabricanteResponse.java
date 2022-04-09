package br.com.letscode.shop.fabricante;

import br.com.letscode.shop.produto.ProdutoResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FabricanteResponse {

    private Long id;
    private String nomeFabricante;
    private ZonedDateTime dataCriacao;
    private Set<ProdutoResponse> produtos;

    public FabricanteResponse(FabricanteEntity fabricanteEntity) {
        this.id = fabricanteEntity.getId();
        this.nomeFabricante = fabricanteEntity.getNomeFabricante();
        this.dataCriacao = fabricanteEntity.getDataCriacao();
        this.produtos = fabricanteEntity.getProdutos().stream().map(produtoEntity -> new ProdutoResponse(produtoEntity)).collect(Collectors.toSet());
    }
}

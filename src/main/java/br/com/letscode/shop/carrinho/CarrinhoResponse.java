package br.com.letscode.shop.carrinho;

import br.com.letscode.shop.produto.ProdutoResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CarrinhoResponse {


    private Long id;
    private Long quantidade;
    private ProdutoResponse produto;
    private Long usuario;

    public CarrinhoResponse(CarrinhoEntity carrinhoEntity) {
        this.id = carrinhoEntity.getId();
        this.produto = new ProdutoResponse(carrinhoEntity.getProduto());
        this.usuario =  carrinhoEntity.getUsuario().getId();
        this.quantidade =  carrinhoEntity.getAmount();
    }
}
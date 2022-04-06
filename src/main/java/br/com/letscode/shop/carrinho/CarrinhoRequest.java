package br.com.letscode.shop.carrinho;


import br.com.letscode.shop.produto.ProdutoEntity;
import br.com.letscode.shop.produto.ProdutoRepository;
import br.com.letscode.shop.usuario.UsuarioRepository;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CarrinhoRequest {

    private ProdutoRepository produtoRepository;
    private UsuarioRepository usuarioRepository;

    private Long usuarioId;
    private int quantidade;
    private String codigoBarra;


    public CarrinhoEntity toEntity() {
       return new CarrinhoEntity(
               produtoRepository.findByCodigoBarra(this.getCodigoBarra()),
               this.getQuantidade(),
               usuarioRepository.findById(this.usuarioId).orElseThrow());
    }
}

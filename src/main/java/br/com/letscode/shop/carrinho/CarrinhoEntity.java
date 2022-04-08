package br.com.letscode.shop.carrinho;

import br.com.letscode.shop.produto.ProdutoEntity;
import br.com.letscode.shop.usuario.UsuarioEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="CARRINHO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    ProdutoEntity produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    UsuarioEntity usuario;

    Long amount;

}
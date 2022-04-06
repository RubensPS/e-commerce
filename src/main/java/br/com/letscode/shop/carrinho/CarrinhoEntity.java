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

    @OneToOne
    @JoinColumn(name = "PRODUTO_ID")
    private ProdutoEntity codigoBarra;

    @Column
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UsuarioEntity userId;

    public CarrinhoEntity (ProdutoEntity codigoBarra, int quantidade, UsuarioEntity userId) {
        this.codigoBarra = codigoBarra;
        this.quantidade = quantidade;
        this.userId = userId;
    }
}

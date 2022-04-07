package br.com.letscode.shop.carrinho;

import br.com.letscode.shop.produto.ProdutoEntity;
import br.com.letscode.shop.usuario.UsuarioEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "CARRINHO_PRODUTO",
            joinColumns = {
                    @JoinColumn(name = "carrinho_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "produto_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    Set<ProdutoEntity> products = new HashSet<>();
    @OneToOne(mappedBy = "carrinho", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UsuarioEntity user;

}

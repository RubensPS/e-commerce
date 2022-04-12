package br.com.letscode.shop.fabricante;

import br.com.letscode.shop.produto.ProdutoEntity;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "FABRICANTE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FabricanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "NAME")
    private String nomeFabricante;

    @Column(name = "DATA_CRIACAO")
    private ZonedDateTime dataCriacao;

    @OneToMany(mappedBy = "fabricante", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<ProdutoEntity> produtos;

    public FabricanteEntity(FabricanteRequest fabricanteRequest) {
        this.nomeFabricante = fabricanteRequest.getNomeFabricante();
        this.dataCriacao = ZonedDateTime.now();
        this.produtos = new HashSet<>();
        }
}

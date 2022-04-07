package br.com.letscode.shop.fabricante;


import br.com.letscode.shop.produto.ProdutoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "FABRICANTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FabricanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String nomeFabricante;

    @OneToMany(mappedBy = "fabricante", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<ProdutoEntity> produtos;
}

package br.com.letscode.shop.fabricante;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}

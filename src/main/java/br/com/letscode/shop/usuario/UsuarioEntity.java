package br.com.letscode.shop.usuario;

import br.com.letscode.shop.carrinho.CarrinhoEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity(name = "USUARIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "NOME_USUARIO")
    private String nomeUsuario;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FUNCAO")
    private String funcao;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "DATA_CRIACAO")
    private ZonedDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private ZonedDateTime dataAtualizacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrinho_id")
    private CarrinhoEntity carrinho;

    public UsuarioEntity(String nomeUsuario, String password, String funcao, String nome, LocalDate dataNascimento) {
        this.nomeUsuario = nomeUsuario;
        this.password = password;
        this.funcao = funcao;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataCriacao = ZonedDateTime.now();
        this.dataAtualizacao = ZonedDateTime.now();
    }
}

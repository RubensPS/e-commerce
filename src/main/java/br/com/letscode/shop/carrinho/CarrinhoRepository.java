package br.com.letscode.shop.carrinho;

import br.com.letscode.shop.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoEntity, Long> {
    List<CarrinhoEntity> findAllByUserId(UsuarioEntity userId);


    //@Query (value = "SELECT p.id, p.nome, p.valor, c.id, c.produto_id, u.nome FROM produto p INNER JOIN carrinho c INNER JOIN usuario u where user_id = ?1", nativeQuery = true )
    //List<CarrinhoEntity> findAllByUserId(UsuarioEntity userId);


    //@Query(value = "SELECT * FROM CARRINHO where user_id = ?1", nativeQuery = true)


}
